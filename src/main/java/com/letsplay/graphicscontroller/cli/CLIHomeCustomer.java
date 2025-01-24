package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class CLIHomeCustomer extends AbstractCLI {
    JoinTournamentController joinTournamentController;
    public CLIHomeCustomer(){
        joinTournamentController = new JoinTournamentController();
    }

    public void start() throws InputException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    case 1 -> reservePitch();
                    case 2 -> joinTournament();
                    case 3 -> joinSharedMatch();
                    case 4 -> viewNotifications();
                    case 5 -> viewProfile();
                    case 6 -> logout();
                    case 7 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Unexpected error");
                }
            }
            catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int showMenu() throws InputException {
        System.out.println("-- Customer Menù --");
        System.out.println("1) Reserve a pitch");
        System.out.println("2) Join tournament");
        System.out.println("3) Join shared match (not available)");
        System.out.println("4) Notifications");
        System.out.println("5) Profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit program");
        return getChoice(1,7);
    }

    private void reservePitch() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

    private void joinTournament() throws InputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
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
                    new CLISelectTournament().start(simpleTournamentBean);
                    break;
                }
            } catch (IOException | InputException | DatabaseException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void joinSharedMatch() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

    private void viewNotifications() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

}
