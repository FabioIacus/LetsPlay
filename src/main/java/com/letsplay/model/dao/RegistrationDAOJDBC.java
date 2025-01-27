package com.letsplay.model.dao;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.RequestException;
import com.letsplay.model.dao.queries.TournamentQueries;
import com.letsplay.model.domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationDAOJDBC implements RegistrationDAO {
    @Override
    public void registerRequest(Registration registration) throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        conn = ConnectionFactory.getConnection();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            int rs = TournamentQueries.createRequest(stmt, registration.getCustomerEmail(), registration.getTeam(), registration.getNumPlayers(), registration.getCaptain(), registration.getManagerEmail(), registration.getTournament());
            if (rs == -1) {
                throw new RequestException("Request not sent, something went wrong!");
            }
        } catch (SQLException | RequestException e) {
            throw e;
        }
        finally {
            //clean-up
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                throw se;
            }
        }
    }

    @Override
    public List<Registration> findTourToAcceptOrDecline(User user) {
        List<Registration> registrations = new ArrayList<>();

        return registrations;
    }

    @Override
    public void changeStatus(Registration reservation) {

    }

    @Override
    public List<Registration> showResponses(User user) throws SQLException, DatabaseException {
        Statement stmt = null;
        Connection conn = null;
        conn = ConnectionFactory.getConnection();
        List<Registration> registrationList = new ArrayList<>();
        RequestStatus status;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = TournamentQueries.retrieveResponses(stmt, user.getEmail());
            //se il rs è vuoto:
            if (!rs.next()) {
                DatabaseException e = new DatabaseException("No requests found!");
                throw e;
            }

            //riposiziono il result set al primo record
            rs.beforeFirst();

            while (rs.next()) {
                status = RequestStatus.valueOf(rs.getString("status").toUpperCase());
                //creazione oggetto con attributi completi
                Registration registration = new Registration(
                        rs.getString("customerEmail"),
                        rs.getString("team"),
                        rs.getInt("numPlayers"),
                        rs.getString("captain"),
                        rs.getString("managerEmail"),
                        status,
                        rs.getString("message"),
                        rs.getString("tournament"));
                //aggiungi il torneo alla lista
                registrationList.add(registration);
            }

            rs.close();

        } catch (SQLException | DatabaseException e) {
            throw e;
        } finally {
            //clean-up
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return registrationList;
    }
}
