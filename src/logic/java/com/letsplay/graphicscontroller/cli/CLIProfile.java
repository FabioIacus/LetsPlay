package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.InputException;
import com.letsplay.model.dao.SessionManager;

public class CLIProfile extends AbstractCLI {
    public void start() {
        
        while (true) {
            int choice;
            try {
                viewInfo();
                choice = showMenu();
                switch (choice){
                    case 1 -> goBack();
                    case 2 -> goHome();
                    case 3 -> viewMessages();
                    case 4 -> logout();
                    case 5 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);}
                    default -> System.out.println("Unexpected error!");
                }
            }
            catch (InputException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private int showMenu() throws InputException {
        System.out.println("What would you like to do?");
        System.out.println("1) Go back");
        System.out.println("2) Go home");
        System.out.println("3) View notifications");
        System.out.println("4) Log out");
        System.out.println("5) Exit program");

        return getChoice(1,5);
    }

    private void viewInfo() {
        System.out.println("-------------------");
        System.out.println("Name: " + SessionManager.getInstance().getCurrentUser().getName());
        System.out.println("Surname: " + SessionManager.getInstance().getCurrentUser().getSurname());
        System.out.println("Email: " + SessionManager.getInstance().getCurrentUser().getEmail());
        System.out.println("Username: " + SessionManager.getInstance().getCurrentUser().getUsername());
        System.out.println("Role: " + SessionManager.getInstance().getCurrentUser().getRole().getId());
        System.out.println("-------------------");
    }

    private void goBack() throws InputException {
        if (SessionManager.getInstance().getCurrentUser().getRole().getId().equals("customer")) {
            new CLIHomeCustomer().start();
        } else {
            new CLIHomeManager().start();
        }
    }

}
