package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;

public class CLIHomePage extends AbstractCLI {

    //metodo iniziale
    public void start() {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> login();
                    case 2 -> signUp();
                    case 3 -> {
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

    //stampa menu iniziale
    private int showMenu() {
        System.out.println("--- What would you like to do? ---");
        System.out.println("1) Log in");
        System.out.println("2) Sign up");
        System.out.println("3) Exit program");

        return getChoice(1, 3);
    }

    private void login() throws InputException {
        new CLILogin().start();
    }

    private void signUp() {
        new CLISignUp().start();
    }

}
