package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;

public class CLIHomeCustomer extends AbstractCLI {

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
                    case 7 -> System.exit(0);
                    default -> throw new InputException("You have entered an incorrect input");
                }
            }
            catch (InputException e) {
                throw new InputException("You have entered an incorrect input");
            }
        }
    }

    private int showMenu() {
        System.out.println("-- Customer Menù --");
        System.out.println("1) Reserve a pitch");
        System.out.println("2) Join tournament");
        System.out.println("3) Join shared match");
        System.out.println("4) View notifications");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit");
        return getChoice(1,7);
    }

    private void reservePitch() throws InputException {
        System.out.println("This feature has not been implemented yet");
        start();
    }

    private void joinTournament() throws InputException {
        System.out.println("This feature has not been implemented yet");
        start();
    }
    private void joinSharedMatch() throws InputException {
        System.out.println("This feature has not been implemented yet");
        start();
    }

    private void viewNotifications() throws InputException {
        System.out.println("This feature has not been implemented yet");
        start();
    }

    private void viewProfile() {

    }

}
