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
                    case 5 -> System.out.println("Hai scelto 5");
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
        System.out.println("-- Manager Menù --");
        System.out.println("1) Create tournament");
        System.out.println("2) Delete tournament");
        System.out.println("3) Edit tournament");
        System.out.println("4) Manage registration request");
        System.out.println("5) View profile");
        System.out.println("6) Log out");
        System.out.println("7) Exit");
        return getChoice(1,7);
    }
}
