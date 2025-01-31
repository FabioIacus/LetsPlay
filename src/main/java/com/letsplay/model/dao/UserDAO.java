package com.letsplay.model.dao;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.UsernameException;
import com.letsplay.model.dao.queries.UserQueries;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;
import com.letsplay.session.ConnectionFactory;

import java.sql.*;

public class UserDAO {
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String ROLE = "Role";

    public User login(User cred) throws DAOException, SQLException {
        Statement stmt = null;
        Connection conn;
        User user;
        Role role;

        //applicazione del notification Factory
        conn = ConnectionFactory.getConnection();

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = UserQueries.login(stmt, cred.getEmail(), cred.getPassword());

            //se il rs è vuoto:
            if (!rs.next()) {
                throw new DAOException("Incorrect email or password!");
            }

            //riposiziono il result set al primo record
            rs.first();

            //ricavo il ruolo dell'utente
            if(rs.getString(ROLE).equals("Customer")) {
                role = Role.CUSTOMER;
            } else {
                role = Role.MANAGER;
            }
            //creo oggetto user
            user = new User(rs.getString(USERNAME), rs.getString(EMAIL), rs.getString(NAME), rs.getString(SURNAME), role);

            //chiusura rs
            rs.close();
        } finally{
            //clean-up
            if (stmt != null)
                stmt.close();
        }

        return user;
    }

    public int signUp(User cred) throws EmailException, DatabaseException, SQLException {
        Statement stmt = null;
        Connection conn;

        //applicazione del notification Factory
        conn = ConnectionFactory.getConnection();

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //controllo se esiste già l'email inserita
            ResultSet rs = UserQueries.checkEmail(stmt, cred.getEmail());
            //se il result set non è vuoto
            if (rs.next()) {
                throw new EmailException("Email already exists!");
            }
            //controllo se esiste già l'username inserito
            ResultSet rs2 = UserQueries.checkUsername(stmt, cred.getUsername());
            //se il result set non è vuoto
            if (rs2.next()) {
                throw new UsernameException("Username already exists!");
            }
            //se l'email e l'username non esistono già posso continuare la registrazione
            int rows = UserQueries.signUp(stmt, cred.getName(), cred.getSurname(), cred.getUsername(), cred.getEmail(), cred.getPassword(), cred.getRole().toString());
            if (rows == -1) {
                throw new DatabaseException("Database error!");
            }
        }
        finally {
            //clean-up
            if (stmt != null)
                stmt.close();
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

        //creo oggetto user
        user = new User(rs.getString(USERNAME), rs.getString(EMAIL), rs.getString(NAME), rs.getString(SURNAME), role);

        return user;
    }

}
