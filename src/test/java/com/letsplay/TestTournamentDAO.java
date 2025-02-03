package com.letsplay;

import com.letsplay.exception.DatabaseException;
import com.letsplay.model.dao.TournamentDAO;
import com.letsplay.model.domain.Tournament;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestTournamentDAO {
    //verifica la presenza di un almeno un torneo nella città indicata
    @Test
    void testFindTournaments() {
        Tournament tournament = new Tournament("roma");
        try {
            List<Tournament> tournamentsList = new TournamentDAO().findTournaments(tournament);
            assertEquals("TorneoCapitale", tournamentsList.getFirst().getName());
        } catch (SQLException e) {
            fail("Unexpected SQLException: " + e.getMessage() + "!");
        } catch (DatabaseException e) {
            fail("No tournament found in the specified city!");
        }
    }
}
