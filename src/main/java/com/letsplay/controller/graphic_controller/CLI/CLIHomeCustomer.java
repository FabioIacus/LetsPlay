package com.letsplay.controller.graphic_controller.CLI;

import com.letsplay.exception.Exception1;

public class CLIHomeCustomer extends AbstractCLI {

    public void start() throws Exception1 {
        while (true) {
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> specifyCity();
                    case 2 -> viewMessages();
                    case 3 -> logout();
                    case 4 -> viewProfile();
                    case 5 -> System.exit(0);
                    default -> throw new Exception1("PORCODIO");
                }
            }
            catch (Exception1 e){
                throw new Exception1("PORCODIO");
            }
        }
    }

    private void viewProfile() {
    }

    private void viewMessages() {
    }

    private int showMenu() {
        System.out.println("What do you want to do?\n");
        System.out.println("1) Chose a city\n");
        System.out.println("2) View messages\n");
        System.out.println("3) Logout\n");
        System.out.println("4) View profile\n");
        System.out.println("5) Quit\n");
        return getChoice(1,5);
    }

    private void specifyCity() {

    }
}
