package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;

import java.util.InputMismatchException;

public class CLIHome extends AbstractCLI {

    //metodo iniziale
    public void start() throws InputException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> new CLILogin().start();
                    case 2 -> new CLISignUp().start();
                    case 3 -> System.exit(0);
                    default -> throw new InputException("You have entered an incorrect input");
                }
            } catch (InputMismatchException e) {
                throw new InputException("You have entered an incorrect input");
            }
        }
    }

    //stampa menu iniziale
    private int showMenu() {
        System.out.println("---Menu---");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");

        return getChoice(1, 3);
    }

}
