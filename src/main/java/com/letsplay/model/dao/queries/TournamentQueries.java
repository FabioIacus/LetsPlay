package com.letsplay.model.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TournamentQueries {
    public static int createRequest(Statement stmt, String customerEmail, String team, int numPlayers, String captain, String managerEmail, String tournament) throws SQLException {
        String sql = "INSERT INTO registration (`customerEmail`, `team`, `numPlayers`, `captain`, `managerEmail`, `tournament`) VALUES ('" + customerEmail + "', '" + team + "', '" + numPlayers + "', '" + captain + "', '" + managerEmail + "', '" + tournament + "')";
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            return rows;
        } else {
            return -1;
        }
    }

    public static ResultSet retrieveTournaments(Statement stmt, String city) throws SQLException {
        String sql = "SELECT * FROM tournament WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveDetails(Statement stmt, String tournament, String footballFacility) throws SQLException {
        String sql = "SELECT * FROM tournament WHERE name = '" + tournament + "' AND footballFacility = '" + footballFacility + "'";
        return stmt.executeQuery(sql);
    }

}
