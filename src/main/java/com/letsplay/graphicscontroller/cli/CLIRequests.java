package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.bean.ResponseBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class CLIRequests extends AbstractCLI{
    public void start(List<RegistrationBean> requestsBeanList) throws DAOException, CsvValidationException, SQLException, InputException, IOException, DatabaseException {
        int choice;
        while (true) {
            int i = 1;
            System.out.println("-- Pending Requests --");
            for (RegistrationBean registrationBean : requestsBeanList){
                System.out.println(i + "   Tournament: " + registrationBean.getTournament());
                System.out.println("    Team: " + registrationBean.getTeam());
                System.out.println("    Number of players: " + registrationBean.getNumPlayers());
                System.out.println("    Captain name: " + registrationBean.getCaptain());
                System.out.println("    Customer email: " + registrationBean.getCustomerEmail());
                i++;
            }
            choice = showMenu();
            switch (choice) {
                case 1 -> acceptOrReject(requestsBeanList);
                case 2 -> goHome();
                case 3 -> goBack();
                case 4 -> viewProfile();
                case 5 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);
                }
                default -> System.out.println("Unexpected error!");
            }
        }
    }

    private int showMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Accept or reject request");
        System.out.println("2. Go home");
        System.out.println("3. Go back");
        System.out.println("4. View profile");
        System.out.println("5. Exit program");

        return getChoice(1,5);
    }

    private void acceptOrReject(List<RegistrationBean> requestsBeanList) {
        if (requestsBeanList.isEmpty()) {
            System.out.println("There are no requests!");
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("Enter the request number: ");
                int request = Integer.parseInt(reader.readLine());
                if (request > requestsBeanList.size() || request < 1) {
                    System.out.println("You have entered an invalid number!");
                } else {
                    System.out.print("Type 1 to accept or 2 to refuse: ");
                    int choice = Integer.parseInt(reader.readLine());
                    if (choice != 1 && choice != 2) {
                        throw new IOException("You have selected an invalid number!");
                    }

                    System.out.print("Enter a message: ");
                    String message = reader.readLine();

                    ResponseBean responseBean = new ResponseBean(choice, message);

                    new JoinTournamentController().manageRequest(requestsBeanList.get(request-1), responseBean);

                    System.out.println("Request processed successfully!");
                    System.out.println("--------------------------------");

                    new CLIHomeManager().start();
                }
            } catch (IOException | EmailException | SQLException | DAOException | InputException | DatabaseException |
                     RequestException | CsvException | NumberFormatException e) {
                System.out.println("An error occured: " + e.getMessage());
            }
        }
    }

    private void goBack() throws InputException, DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        new CLIHomeManager().start();
    }
}
