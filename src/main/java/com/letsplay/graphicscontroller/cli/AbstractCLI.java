package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.controller.LoginController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.letsplay.session.SessionManager;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.letsplay.model.domain.Role.CUSTOMER;

public abstract class AbstractCLI {

    protected int getChoice(int min, int max) {
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print("Please enter your choice: ");
                //gestisce input non numerico
                if (!input.hasNextInt()) {
                    input.next();
                    throw new InputMismatchException("You have entered an incorrect input!");
                }

                choice = input.nextInt();
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    throw new InputException("Invalid option. You must enter a number between " + min + " and " + max + "!");
                }
            } catch (InputMismatchException | InputException e) {
                System.out.println(e.getMessage());
            }
        }

        return choice;
    }

    protected void logout() {
        new LoginController().logout();
        System.out.println("You are about to be logged out...");
        new CLIHomePage().start();
    }

    protected void viewNotifications() throws InputException, DAOException, SQLException, IOException, DatabaseException, CsvValidationException {
        try {
            if (SessionManager.getInstance().getCurrentUser().getRole() == CUSTOMER) {
                List<RegistrationBean> registrationBeanList = new JoinTournamentController().getResponses();
                if (registrationBeanList.isEmpty()) {
                    System.out.println("No requests found!");
                    return;
                }
                new CLINotifications().start(registrationBeanList);
            } else {
                List<RegistrationBean> requestsBeanList = new JoinTournamentController().getRequests();
                if (requestsBeanList.isEmpty()) {
                    System.out.println("No requests found!");
                    return;
                }
                new CLIRequests().start(requestsBeanList);
            }
        } catch (InputException | DAOException | SQLException | IOException | DatabaseException | CsvValidationException e){
            System.out.println(e.getMessage());
        }
    }

    protected void viewProfile() {
        new CLIProfile().start();
    }

    protected void goHome() throws InputException, DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        if (SessionManager.getInstance().getCurrentUser().getRole().getId().equals("customer")) {
            new CLIHomeCustomer().start();
        } else {
            new CLIHomeManager().start();
        }
    }

}
