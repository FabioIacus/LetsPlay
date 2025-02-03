package com.letsplay.controller;

import com.letsplay.bean.*;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.RequestException;
import com.letsplay.model.dao.RegistrationDAO;
import com.letsplay.model.dao.RegistrationDAOFactory;
import com.letsplay.session.SessionManager;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.RequestStatus;
import com.letsplay.model.domain.Tournament;
import com.letsplay.model.dao.TournamentDAO;
import com.letsplay.model.domain.User;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

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

    public TournamentBean showDetails(SelectedTournamentBean selectedTournamentBean) throws DatabaseException, SQLException {
        Tournament selectedTournament = new Tournament();
        selectedTournament.setName(selectedTournamentBean.getTournament());
        selectedTournament.setFootballFacility(selectedTournamentBean.getFootballFacility());
        TournamentBean tournamentBean;

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
                details.getParticipationFee()
        );
        tournamentBean.setNumTeams(details.getNumberTeams());
        tournamentBean.setPrize(details.getPrize());
        tournamentBean.setRequirements(details.getRequirements());
        tournamentBean.setType(details.getType().getValue());
        tournamentBean.setManagerEmail(details.getManagerEmail());
        return tournamentBean;
    }

    public void signUpTeam(RegistrationBean registrationBean) throws IOException, RequestException, SQLException, CsvException, EmailException {
        Registration registration = new Registration(
                registrationBean.getCustomerEmail(),
                registrationBean.getManagerEmail(),
                registrationBean.getTeam(),
                registrationBean.getNumPlayers(),
                registrationBean.getCaptain(),
                registrationBean.getTournament()
        );
        RegistrationDAOFactory factory = new RegistrationDAOFactory();
        RegistrationDAO registrationDAO = factory.createRegistrationDAO();
        registrationDAO.registerRequest(registration);
        registration.notifyUser();
    }

    public List<RegistrationBean> getResponses() throws DAOException, SQLException, IOException, DatabaseException, CsvValidationException {
        User user = SessionManager.getInstance().getCurrentUser();
        List<RegistrationBean> registrationBeanList = new ArrayList<>();
        RegistrationDAOFactory registrationDAOFactory= new RegistrationDAOFactory();
        RegistrationDAO registrationDAO = registrationDAOFactory.createRegistrationDAO();
        List<Registration> registrationList = registrationDAO.showResponses(user);
        for (Registration registration : registrationList) {
            RegistrationBean registrationBean = new RegistrationBean(
                    registration.getTournament(),
                    registration.getTeam(),
                    registration.getNumPlayers(),
                    registration.getCaptain(),
                    registration.getManagerEmail(),
                    registration.getStatus());
            registrationBean.setMessage(registration.getMessage());
            registrationBeanList.add(registrationBean);
        }
        return registrationBeanList;
    }

    public List<RegistrationBean> getRequests() throws IOException, SQLException, DatabaseException, CsvValidationException {
        User user = SessionManager.getInstance().getCurrentUser();
        List<RegistrationBean> registrationBeanList = new ArrayList<>();
        RegistrationDAOFactory registrationDAOFactory= new RegistrationDAOFactory();
        RegistrationDAO registrationDAO = registrationDAOFactory.createRegistrationDAO();
        List<Registration> requestsList = registrationDAO.showRequests(user);
        for (Registration request : requestsList) {
            RegistrationBean registrationBean = new RegistrationBean(
                    request.getTournament(),
                    request.getTeam(),
                    request.getNumPlayers(),
                    request.getCaptain(),
                    request.getCustomerEmail());
            registrationBean.setMessage(request.getMessage());
            registrationBean.setManagerEmail(request.getManagerEmail());

            registrationBeanList.add(registrationBean);
        }
        return registrationBeanList;
    }

    public void manageRequest(RegistrationBean request, ResponseBean responseBean) throws IOException, SQLException, CsvException {
        Registration registration;
        //se la richiesta viene accettata
        if (responseBean.getChoice() == 1) {
            registration = new Registration(
                    request.getCustomerEmail(),
                    request.getTeam(),
                    request.getNumPlayers(),
                    request.getCaptain(),
                    request.getTournament(),
                    responseBean.getMessage(),
                    RequestStatus.ACCEPTED
            );
        } else {
            registration = new Registration(
                    request.getCustomerEmail(),
                    request.getTeam(),
                    request.getNumPlayers(),
                    request.getCaptain(),
                    request.getTournament(),
                    responseBean.getMessage(),
                    RequestStatus.REJECTED
            );
        }
        registration.setManagerEmail(request.getManagerEmail());

        RegistrationDAOFactory factory = new RegistrationDAOFactory();
        RegistrationDAO registrationDAO = factory.createRegistrationDAO();

        registrationDAO.acceptOrReject(registration);
        registration.notifyUser();
    }
}
