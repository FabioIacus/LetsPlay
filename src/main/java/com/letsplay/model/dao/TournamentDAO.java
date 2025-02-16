package com.letsplay.model.dao;

import com.letsplay.exception.DatabaseException;
import com.letsplay.model.dao.queries.TournamentQueries;
import com.letsplay.model.domain.Tournament;
import com.letsplay.model.domain.Type;
import com.letsplay.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO {
        public List<Tournament> findTournaments(Tournament tournamentsCity) throws DatabaseException, SQLException {
            Statement stmt = null;
            Connection conn;
            List<Tournament> tournaments = new ArrayList<>();
            conn = ConnectionFactory.getConnection();
            Type type;

            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                //cerca tornei
                ResultSet rs = TournamentQueries.retrieveTournaments(stmt, tournamentsCity.getCity());

                //se il rs è vuoto:
                if (!rs.next()) {
                    throw new DatabaseException("No tournament found in the specified city!");
                }

                //riposiziono il result set al primo record
                rs.beforeFirst();

                //inserimento tornei nella lista
                while (rs.next()) {
                    type = Type.valueOf(rs.getString("type").toUpperCase().replace(" ",""));
                    //creazione oggetto con attributi completi
                    Tournament tournament = new Tournament(
                            rs.getString("name"),
                            rs.getString("footballFacility"),
                            rs.getString("address"),
                            rs.getDate("startDate"),
                            rs.getDate("endDate"),
                            type);
                    //aggiungi il torneo alla lista
                    tournaments.add(tournament);
                }

                rs.close();
            } finally {
                //clean-up
                if (stmt != null)
                    stmt.close();
            }

            return tournaments;
        }

        public Tournament tournamentDetails(Tournament selectedTournament) throws DatabaseException, SQLException {
            Statement stmt = null;
            Connection conn;
            conn = ConnectionFactory.getConnection();
            Tournament tournament;
            Type type;

            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                //cerca dettagli del torneo
                ResultSet rs = TournamentQueries.retrieveDetails(stmt, selectedTournament.getName(), selectedTournament.getFootballFacility());

                //se il rs è vuoto:
                if (!rs.next()) {
                    throw new DatabaseException("No tournament found in the specified city!");
                }

                //riposiziono il result set al primo record
                rs.first();

                type = Type.valueOf(rs.getString("type").toUpperCase().replace(" ",""));
                //creazione oggetto con attributi completi
                tournament = new Tournament(
                        rs.getString("name"),
                        rs.getString("footballFacility"),
                        rs.getString("address"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getInt("participationFee"),
                        rs.getInt("numberTeams")
                );
                tournament.setPrize(rs.getString("prize"));
                tournament.setRequirements(rs.getString("requirements"));
                tournament.setCity(rs.getString("city"));
                tournament.setType(type);
                tournament.setManagerEmail(rs.getString("managerEmail"));

                rs.close();
            } finally {
                //clean-up
                if (stmt != null)
                    stmt.close();
            }

            return tournament;
        }
}
