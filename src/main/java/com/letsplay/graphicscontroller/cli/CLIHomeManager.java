package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;

public class CLIHomeManager extends AbstractCLI {
    public void start() throws InputException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    case 1 -> viewRequests();
                    case 2 -> createTournament();
                    case 3 -> deleteTournament();
                    case 4 -> editTournament();
                    case 5 -> viewProfile();
                    case 6 -> logout();
                    case 7 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Unexpected error!");
                }
            }
            catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int showMenu() throws InputException {
        System.out.println("-- Manager Menù --");
        System.out.println("1) Manage registration requests");
        System.out.println("2) Create tournament");
        System.out.println("3) Delete tournament");
        System.out.println("4) Edit tournament");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit program");
        return getChoice(1,7);
    }

    private void viewRequests() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

    private void createTournament() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

    private void deleteTournament() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

    private void editTournament() throws InputException {
        System.out.println("This feature has not been implemented yet!");
    }

}
