package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;

public class CLIHomeManager extends AbstractCLI {
    public void start() throws InputException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    //CONTINUARE A MODIFICARE
                    case 1 -> System.out.println("Hai scelto 1");
                    case 2 -> System.out.println("Hai scelto 2");
                    case 3 -> System.out.println("Hai scelto 3");
                    case 4 -> System.out.println("Hai scelto 4");
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
        System.out.println("1) Create tournament");
        System.out.println("2) Delete tournament");
        System.out.println("3) Edit tournament");
        System.out.println("4) Manage registration request");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit program");
        return getChoice(1,7);
    }
}
