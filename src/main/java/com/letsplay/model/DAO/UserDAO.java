package com.letsplay.model.DAO;

import com.letsplay.exception.DAOException;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return user;
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
