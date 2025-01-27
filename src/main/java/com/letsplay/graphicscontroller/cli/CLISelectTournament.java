package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CLISelectTournament extends AbstractCLI {
    public void start(List<SimpleTournamentBean> listOfTournaments) {
        while (true) {
            int choice;
            int size = listOfTournaments.size();
            int tournament = -1;
            try {
                choice = showMenu(listOfTournaments);
                //se scelgo un torneo
                if (choice <= size){
                    tournament = choice-1;
                    choice = 1;
                } else {
                    choice -= size-1;
                }
                switch (choice) {
                    case 1 -> viewDetails(listOfTournaments, tournament);
                    case 2 -> goHome();
                    case 3 -> goBack();
                    case 4 -> viewNotifications();
                    case 5 -> viewProfile();
                    case 6 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Unexpected error!");
                }
            } catch (InputException | DatabaseException | DAOException | SQLException | IOException | CsvValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //stampa dei tornei disponibili con le altre opzioni
    private int showMenu(List<SimpleTournamentBean> tournaments) throws InputException {
        int i = 1;
        System.out.println("Select a tournament: ");
        for (SimpleTournamentBean tournament : tournaments){
            System.out.println(i + ".  Name: " + tournament.getName());
            System.out.println("    Start date: " + tournament.getStartDate());
            System.out.println("    End date: " + tournament.getEndDate());
            System.out.println("    Football Facility: " + tournament.getFootballFacility());
            System.out.println("    Address: " + tournament.getAddress());
            System.out.println("    Type: " + tournament.getType());
            i++;
        }
        System.out.println("Other options: ");
        System.out.println(i + ". Go home");
        System.out.println(i+1 + ". Go back");
        System.out.println(i+2 + ". View notifications");
        System.out.println(i+3 + ". View profile");
        System.out.println(i+4 + ". Exit program");

        return getChoice(1, i+4);
    }

    private void goBack() throws InputException {
        new CLIHomeCustomer().start();
    }

    private void viewDetails(List<SimpleTournamentBean> listOfTournaments, int tournament) throws DatabaseException {
        new CLIViewDetails().start(listOfTournaments.get(tournament).getName(), listOfTournaments.get(tournament).getFootballFacility(), listOfTournaments);
    }
}
