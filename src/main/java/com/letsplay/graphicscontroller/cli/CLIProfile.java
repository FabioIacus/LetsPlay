package com.letsplay.graphicscontroller.cli;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.letsplay.session.SessionManager;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;

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
                    case 3 -> viewNotifications();
                    case 4 -> {
                        System.out.println("Exiting the application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Unexpected error!");
                }
            }
            catch (InputException | DAOException | SQLException | IOException | DatabaseException | CsvValidationException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private int showMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1) Go back");
        System.out.println("2) Go home");
        System.out.println("3) View notifications");
        System.out.println("4) Exit program");

        return getChoice(1,4);
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

    private void goBack() throws InputException, DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        if (SessionManager.getInstance().getCurrentUser().getRole().getId().equals("customer")) {
            new CLIHomeCustomer().start();
        } else {
            new CLIHomeManager().start();
        }
    }

}
