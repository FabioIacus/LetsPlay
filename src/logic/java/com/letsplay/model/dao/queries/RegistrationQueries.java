package com.letsplay.model.dao.queries;

import com.letsplay.model.domain.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationQueries {
    public static ResultSet createRequest(Statement stmt, String customerEmail, String team, int numPlayers, String captain, String managerEmail, int tournament, RequestStatus status) throws SQLException {
        String sql = "INSERT INTO registration ('customerEmail`, `team`, `numPlayers`, `captain`, `managerEmail`, `tournament`, `status`) VALUES ('" + customerEmail + "', '" + team + "', '" + numPlayers + "', '" + captain + "', '" + managerEmail + "', '" + tournament + "', '" + status + "')";
        return stmt.executeQuery(sql);
    }

    public static String getTournamentString(Statement stmt, int id) throws SQLException {
        //SCRIVERE QUERY SQL PER RECUPERARE IL NOME DEL TORNEO DALL'ID
        String sql = "SELECT * FROM tournament WHERE ";
    }
}
