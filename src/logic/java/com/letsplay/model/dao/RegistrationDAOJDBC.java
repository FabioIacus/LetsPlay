package com.letsplay.model.dao;

import com.letsplay.exception.RequestException;
import com.letsplay.model.dao.queries.TournamentQueries;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
