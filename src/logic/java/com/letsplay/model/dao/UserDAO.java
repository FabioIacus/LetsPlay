package com.letsplay.model.dao;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.RegistrationException;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String ROLE = "Role";

    public User login(User cred) throws DAOException, SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        try {
            //applicazione del pattern Factory
            conn = ConnectionFactory.getConnection();

            //stringa SQL
            String sql = "SELECT * FROM User WHERE " + EMAIL + " = ? AND " + PASSWORD + " = ?;";
            //utilizzo di prepare statement poiché più sicuro di statement
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, cred.getEmail());
            stmt.setString(2, cred.getPassword());
            //ottengo il risultato della query
            ResultSet rs = stmt.executeQuery();

            //se il rs è vuoto:
            if (!rs.first()) {
                throw new DAOException("Incorrect email or password");
            }
            //riposiziono il result set al primo record
            rs.first();
            //chiamo il metodo per popolare i campi di user
            user = getUser(rs);
            //chiusura rs
            rs.close();
        }
        finally{
            //clean-up
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger.getAnonymousLogger().log(Level.INFO, "Error: ", se.getMessage());
            }
        }

        return user;
    }

    public int signUp(User cred) throws DAOException, SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        conn = ConnectionFactory.getConnection();
        try {
            String sql1 = "SELECT * FROM user WHERE " + EMAIL + " = ?";
            stmt = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, cred.getEmail());
            ResultSet rs = stmt.executeQuery();
            //se il result set non è vuoto
            if (rs.next()) {
                throw new EmailException("Email already exists");
            }
        } catch(EmailException e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Error: ", e.getMessage());
        }
        try {
            String sql2 = "INSERT INTO user (" + NAME + ", " + SURNAME +", " + USERNAME +", " + EMAIL + ", " + PASSWORD + ", " + ROLE + ")"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, cred.getName());
            stmt.setString(2, cred.getSurname());
            stmt.setString(3, cred.getUsername());
            stmt.setString(4, cred.getEmail());
            stmt.setString(5, cred.getPassword());
            stmt.setString(6, cred.getRole().toString());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RegistrationException("Registration failed");
            } else {
                Logger.getAnonymousLogger().log(Level.INFO, "Registration completed");
            }
        } catch(SQLException | RegistrationException e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Error: ", e.getMessage());
        }
        finally {
            //clean-up
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger.getAnonymousLogger().log(Level.INFO, "Error: ", se.getMessage());
            }
        }
        return 0;
    }

    protected User getUser(ResultSet rs) throws SQLException {
        User user;
        Role role;

        //ricavo il ruolo dell'utente
        if(rs.getString(ROLE).equals("Customer")) {
            role = Role.CUSTOMER;
        } else {
            role = Role.MANAGER;
        }

        user = new User(rs.getString(USERNAME), rs.getString(EMAIL), rs.getString(NAME), rs.getString(SURNAME), role);

        return user;
    }

}
