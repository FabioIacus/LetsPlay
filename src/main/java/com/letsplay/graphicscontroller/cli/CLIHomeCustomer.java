package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class CLIHomeCustomer extends AbstractCLI {
    private static final String NOTIMPLEMENTED = "This feature has not been implemented yet!";
    JoinTournamentController joinTournamentController;
    private CLISelectTournament cliSelectTournament;
    //procedimento corretto, non devo fare new nei metodi
    public CLIHomeCustomer(){
        joinTournamentController = new JoinTournamentController();
        cliSelectTournament = new CLISelectTournament();
    }

    public void start() throws InputException {
        //procedimento sbagliato, non va il true ma bisogna usare una variabile booleana
        while (true) {
            int choice;
            try {
                choice = showMenu();
                executeChoice(choice);
            } catch (InputException | DAOException | SQLException | IOException | DatabaseException |
                     CsvValidationException e) {
                handleException(e);
            }
        }
    }

    private void executeChoice(int choice) throws InputException, DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        switch (choice) {
            case 1, 3 -> showNotImplementedMessage();
            case 2 -> joinTournament();
            case 4 -> viewNotifications();
            case 5 -> viewProfile();
            case 6 -> logout();
            case 7 -> exitApplication();
            default -> System.out.println("Unexpected error");
        }
    }

    private int showMenu() {
        System.out.println("-- Customer Menù --");
        System.out.println("1) Reserve a pitch (not available)");
        System.out.println("2) Join tournament");
        System.out.println("3) Join shared match (not available)");
        System.out.println("4) View notifications");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit program");
        return getChoice(1,7);
    }

    private void joinTournament() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isTournamentSelected = false;
        while (!isTournamentSelected) {
            try {
                //ricava città
                System.out.print("Please specify a city to search in: ");
                String city = reader.readLine();
                //crea bean contenente la città
                SearchTournamentBean searchTournamentBean = new SearchTournamentBean(city);

                //chiama controller che ritornerà una lista di simpleTournamentBean
                List<SimpleTournamentBean> simpleTournamentBean = joinTournamentController.searchTournaments(searchTournamentBean);

                //se la lista è vuota
                if (simpleTournamentBean.isEmpty()) {
                    System.out.println("No tournament found, choose another city!");
                } else {
                    //passa alla selezione del torneo
                    cliSelectTournament.start(simpleTournamentBean);
                    isTournamentSelected = true;
                }
            } catch (IOException | InputException | DatabaseException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showNotImplementedMessage() {
        System.out.println(NOTIMPLEMENTED);
    }

    private void exitApplication() {
        System.out.println("Exiting the application...");
        System.exit(0);
    }

    private void handleException(Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
