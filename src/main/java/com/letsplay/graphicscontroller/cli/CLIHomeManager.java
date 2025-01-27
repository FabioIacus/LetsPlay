package com.letsplay.graphicscontroller.cli;

public class CLIHomeManager extends AbstractCLI {
    private static final String NOTIMPLEMENTED = "This feature has not been implemented yet!";
    public void start() {
        while (true) {
            int choice;
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
    }

    private int showMenu() {
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

    private void viewRequests() {
        System.out.println(NOTIMPLEMENTED);
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
