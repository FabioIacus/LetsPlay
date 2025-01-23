package com.letsplay.controller;

import com.letsplay.bean.*;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.RequestException;
import com.letsplay.model.dao.RegistrationDAO;
import com.letsplay.model.dao.RegistrationDAOFactory;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.Tournament;
import com.letsplay.model.dao.TournamentDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinTournamentController {
    public List<SimpleTournamentBean> searchTournaments(SearchTournamentBean searchTournamentBean) throws DatabaseException, SQLException {
        //creazione oggetto di tipo Tournament
        Tournament tournament = new Tournament(searchTournamentBean.getCity());
        //invoco il DAO per ricavare la lista dei tornei
        List<Tournament> tournamentsList = new TournamentDAO().findTournaments(tournament);
        //crea un oggetto simpleTournamentBean per mostrare le informazioni principali del torneo
        List<SimpleTournamentBean> simpleTournamentBeanList = new ArrayList<>();

        for (Tournament t : tournamentsList) {
            SimpleTournamentBean simpleTournamentBean = new SimpleTournamentBean(t.getName(), t.getStartDate(), t.getEndDate(), t.getFootballFacility(), t.getAddress(), t.getType().getValue());
            simpleTournamentBeanList.add(simpleTournamentBean);
        }

        return simpleTournamentBeanList;
    }

    public TournamentBean showDetails(SelectedTournamentBean selectedTournamentBean) throws DatabaseException {
        Tournament selectedTournament = new Tournament();
        selectedTournament.setName(selectedTournamentBean.getTournament());
        selectedTournament.setFootballFacility(selectedTournamentBean.getFootballFacility());
        TournamentBean tournamentBean = null;
        try {
            //recupera i dettagli del torneo selezionato
            Tournament details = new TournamentDAO().tournamentDetails(selectedTournament);
            if (details == null) {
                throw new DatabaseException("No details found. Try later.");
            }
            tournamentBean = new TournamentBean(
                    details.getName(),
                    details.getFootballFacility(),
                    details.getCity(),
                    details.getAddress(),
                    details.getStartDate(),
                    details.getEndDate(),
                    details.getParticipationFee(),
                    details.getNumberTeams(),
                    details.getPrize(),
                    details.getRequirements(),
                    details.getType().getValue(),
                    details.getManagerEmail()
            );
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tournamentBean;
    }

    public void signUpTeam(RegistrationBean registrationBean) throws IOException, RequestException, SQLException {
        int result;
        Registration registration = new Registration(
                registrationBean.getCustomerEmail(),
                registrationBean.getManagerEmail(),
                registrationBean.getTeam(),
                registrationBean.getNumPlayers(),
                registrationBean.getCaptain(),
                registrationBean.getTournament()
        );
        RegistrationDAOFactory factory = new RegistrationDAOFactory();
        try {
            RegistrationDAO registrationDAO = factory.createRegistrationDAO();
            registrationDAO.registerRequest(registration);
        } catch (RequestException | SQLException e) {
            throw e;
        }
    }
}
