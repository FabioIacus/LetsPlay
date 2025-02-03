package com.letsplay.model.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TournamentQueries {
    private static final String TOURNAMENT = "and tournament";
    private TournamentQueries() {}

    public static int createRequest(Statement stmt, String customerEmail, String team, int numPlayers, String captain, String managerEmail, String tournament) throws SQLException {
        String sql = "INSERT INTO registration (`customerEmail`, `team`, `numPlayers`, `captain`, `managerEmail`, `tournament`) VALUES ('" + customerEmail + "', '" + team + "', '" + numPlayers + "', '" + captain + "', '" + managerEmail + "', '" + tournament + "')";
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            return rows;
        } else {
            return -1;
        }
    }

    public static ResultSet check(Statement stmt, String email, String tournament) throws SQLException {
        String sql = "SELECT * FROM registration WHERE customerEmail = '" + email + "' " + TOURNAMENT + " = '" + tournament + "' AND (status = 'Pending' OR status = 'Accepted')";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveResponses(Statement stmt, String email) throws SQLException {
        String sql = "SELECT * FROM registration WHERE customerEmail = '" + email + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveRequests(Statement stmt, String email) throws SQLException {
        String sql = "SELECT * FROM registration WHERE managerEmail = '" + email + "' and status = 'Pending'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveTournaments(Statement stmt, String city) throws SQLException {
        String sql = "SELECT * FROM tournament WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveDetails(Statement stmt, String tournament, String footballFacility) throws SQLException {
        String sql = "SELECT * FROM tournament WHERE name = '" + tournament + "' AND footballFacility = '" + footballFacility + "'";
        return stmt.executeQuery(sql);
    }

    public static int acceptRequest(Statement stmt, String customerEmail, String tournament, String message) throws SQLException {
        String sql = "UPDATE registration SET status = 'Accepted', message = '" + message + "' WHERE customerEmail = '" + customerEmail + "' " + TOURNAMENT + " = '" + tournament + "' AND status = 'Pending'";
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            return rows;
        } else {
            return -1;
        }
    }

    public static int rejectRequest(Statement stmt, String customerEmail, String tournament, String message) throws SQLException {
        String sql = "UPDATE registration SET status = 'Rejected', message = '" + message + "'WHERE customerEmail = '" + customerEmail + "' " + TOURNAMENT + " = '" + tournament + "' AND status = 'Pending'";
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            return rows;
        } else {
            return -1;
        }
    }
}
