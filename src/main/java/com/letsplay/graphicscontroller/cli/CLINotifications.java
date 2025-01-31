package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CLINotifications extends AbstractCLI {
    public void start(List<RegistrationBean> registrationBeanList) throws InputException, DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        int choice;
        int i = 1;
        while (true) {
            System.out.println("-- Requests Sent --");
            for (RegistrationBean registrationBean : registrationBeanList){
                System.out.println(i + "   Tournament: " + registrationBean.getTournament());
                System.out.println("    Registered team: " + registrationBean.getTeam());
                System.out.println("    Number of players: " + registrationBean.getNumPlayers());
                System.out.println("    Captain name: " + registrationBean.getCaptain());
                System.out.println("    Manager email: " + registrationBean.getManagerEmail());
                System.out.println("    Request status: " + registrationBean.getStatus());
                if (registrationBean.getMessage() == null || registrationBean.getMessage().isEmpty() || registrationBean.getMessage().isBlank()) {
                    System.out.println("    Message: No message sent by manager");
                } else {
                    System.out.println("    Message: " + registrationBean.getMessage());
                }
                i++;
            }
            choice = showMenu();
            switch (choice) {
                case 1 -> goHome();
                case 2 -> goBack();
                case 3 -> viewProfile();
                case 4 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);
                }
                default -> System.out.println("Unexpected error!");
            }
        }
    }

    private int showMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Go home");
        System.out.println("2. Go back");
        System.out.println("3. View profile");
        System.out.println("4. Exit program");

        return getChoice(1,4);
    }

    private void goBack() throws InputException {
        new CLIHomeCustomer().start();
    }
}
