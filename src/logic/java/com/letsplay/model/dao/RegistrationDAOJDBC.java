package com.letsplay.model.dao;

import com.letsplay.exception.DAOException;
import com.letsplay.model.dao.queries.RegistrationQueries;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RegistrationDAOJDBC implements RegistrationDAO {
    @Override
    public int register(Registration registration) {
        Statement stmt = null;
        Connection conn = null;
        int result = -1;
        conn = ConnectionFactory.getConnection();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //OTTIENI PRIMA IL NOME DEL TORNEO DALL'ID E POI ESEGUI LA CREAZIONE DELLA RICHIESTA
            ResultSet rs = RegistrationQueries.getTournamentString();
            ResultSet rs = RegistrationQueries.createRequest(stmt, registration.getCustomerEmail(), registration.getTeam(), registration.getNumPlayers(), registration.getCaptain(), registration.getManagerEmail(), registration.getTournament(), registration.getStatus());
            if (!rs.first()) {
                throw new DAOException("Incorrect email or password");
            }
            //riposiziono il result set al primo record
            rs.first();
        } catch (SQLException | DAOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<Registration> findTourToAcceptOrDecline(User user) {
        return List.of();
    }

    @Override
    public void changeStatus(Registration reservation) {

    }

    @Override
    public List<Registration> findTourStatus(User user) {
        return List.of();
    }
}
