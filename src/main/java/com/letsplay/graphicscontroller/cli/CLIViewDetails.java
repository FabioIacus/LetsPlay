package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.SelectedTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.bean.TournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;

import java.util.List;

public class CLIViewDetails extends AbstractCLI {
    public void start(String tournament, String footballFacility, List<SimpleTournamentBean> tournamentList) throws DatabaseException {
        SelectedTournamentBean selectedTournamentBean = new SelectedTournamentBean(tournament, footballFacility);
        TournamentBean tournamentBean = new JoinTournamentController().showDetails(selectedTournamentBean);
        showDetails(tournamentBean);
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> new CLITournamentSignUp().start(tournamentBean);
                    case 2 -> goHome();
                    case 3 -> goBack(tournamentList);
                    case 4 -> viewMessages();
                    case 5 -> logout();
                    case 6 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Unexpected error!");
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showDetails(TournamentBean tournamentBean) {
        System.out.println("-- Tournament Details --");
        System.out.println("Tournament name: " + tournamentBean.getName());
        System.out.println("Football facility: " + tournamentBean.getFootballFacility());
        System.out.println("City: " + tournamentBean.getCity());
        System.out.println("Address: " + tournamentBean.getAddress());
        System.out.println("Start date: " + tournamentBean.getStartDate());
        System.out.println("End date: " + tournamentBean.getEndDate());
        System.out.println("Participation fee: " + tournamentBean.getParticipationFee() + "€");
        System.out.println("Total number of teams: " + tournamentBean.getNumTeams());
        System.out.println("Prize for the winning team: " + tournamentBean.getPrize());
        System.out.println("Requirements: " + tournamentBean.getRequirements());
        System.out.println("Type: " + tournamentBean.getType());
        System.out.println("Manager Email: " + tournamentBean.getManagerEmail());
    }

    private int showMenu() throws InputException {
        System.out.println("-------------------------");
        System.out.println("What would you like to do?");
        System.out.println("1. Sign up");
        System.out.println("2. Go home");
        System.out.println("3. Go back");
        System.out.println("4. View notifications");
        System.out.println("5. View profile");
        System.out.println("6. Exit program");

        return getChoice(1, 6);
    }

    private void goBack(List<SimpleTournamentBean> tournamentList) throws InputException {
        new CLISelectTournament().start(tournamentList);
    }
}
