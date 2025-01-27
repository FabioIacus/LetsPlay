package com.letsplay.model.dao.queries;

import java.sql.*;

public class UserQueries {
    private UserQueries() {}

    public static ResultSet login(Statement stmt, String email, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE Email = '" + email + "' AND Password = '" + password + "'";
        return stmt.executeQuery(sql);
    }
    public static ResultSet checkEmail(Statement stmt, String email) throws SQLException {
        String sql = "SELECT * FROM User WHERE Email = '" + email + "'";
        return stmt.executeQuery(sql);
    }
    public static ResultSet checkUsername(Statement stmt, String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE Username = '" + username + "'";
        return stmt.executeQuery(sql);
    }
    public static int signUp(Statement stmt, String name, String surname, String username, String email, String password, String role) throws SQLException {
        String sql = "INSERT INTO User (`Name`, `Surname`, `Username`, `Email`, `Password`, `Role`) VALUES ('" + name + "', '" + surname + "', '" + username + "', '" + email + "', '" + password + "', '" + role + "')";
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            return rows;
        } else {
            return -1;
        }
    }
}
