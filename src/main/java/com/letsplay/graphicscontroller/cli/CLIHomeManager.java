package com.letsplay.graphicscontroller.cli;

import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;

public class CLIHomeManager extends AbstractCLI {
    private static final String NOTIMPLEMENTED = "This feature has not been implemented yet!";
    JoinTournamentController joinTournamentController;
    public CLIHomeManager(){
        joinTournamentController = new JoinTournamentController();
    }

    public void start() throws DAOException, CsvValidationException, SQLException, InputException, IOException, DatabaseException {
        while (true) {
            int choice;
            choice = showMenu();
            switch (choice) {
                case 1 -> createTournament();
                case 2 -> deleteTournament();
                case 3 -> editTournament();
                case 4 -> viewNotifications();
                case 5 -> viewProfile();
                case 6 -> logout();
                case 7 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);
                }
                default -> System.out.println("Unexpected error!");
            }
        }
    }

    private int showMenu() {
        System.out.println("-- Manager Menù --");
        System.out.println("1) Create tournament (not available)");
        System.out.println("2) Delete tournament (not available)");
        System.out.println("3) Edit tournament (not available)");
        System.out.println("4) View notifications");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit program");
        return getChoice(1,7);
    }

    private void createTournament() {
        System.out.println(NOTIMPLEMENTED);
    }

    private void deleteTournament() {
        System.out.println(NOTIMPLEMENTED);
    }

    private void editTournament() {
        System.out.println(NOTIMPLEMENTED);
    }

}
