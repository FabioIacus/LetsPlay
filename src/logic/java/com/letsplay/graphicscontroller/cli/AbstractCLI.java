package com.letsplay.graphicscontroller.cli;

import com.letsplay.controller.LoginController;
import com.letsplay.exception.InputException;
import com.letsplay.model.dao.SessionManager;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.letsplay.model.domain.Role.CUSTOMER;

public abstract class AbstractCLI {

    protected int getChoice(int min, int max) throws InputException {
        Scanner input = new Scanner(System.in);
        int choice = 0;
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

    protected void logout() throws InputException {
        new LoginController().logout();
        System.out.println("You are about to be logged out...");
        new CLIHomePage().start();
    }

    protected void viewMessages(){
        /*if (SessionManager.getInstance().getCurrentUser().getRole() == CUSTOMER) {
            List<RequestsInfoBean> tourInfo = new JoinTourController().showMessages();
            new CLIMessagesGraphicController().start(tourInfo);
        } else {
            CLIPrinter.printNumbers(1);
            CLIPrinter.printMessage("Show messages\n");
            CLIPrinter.printNumbers(2);
            CLIPrinter.printMessage("Show requests\n");
            int choice = getMenuChoice(1, 2);
            if (choice == 1) {
                List<RequestsInfoBean> tourInfo = new JoinTourController().showMessages();
                new CLIMessagesGraphicController().start(tourInfo);
            } else if (choice == 2) {
                List<ReservationInfoBean> tourInfo = new JoinTourController().showRequests();
                new CLIRequestsGraphicController().start(tourInfo);
            }
        }*/
    }

    protected void viewProfile() {
        new CLIProfile().start();
    }

    protected void goHome() throws InputException {
        if (SessionManager.getInstance().getCurrentUser().getRole().getId().equals("customer")) {
            new CLIHomeCustomer().start();
        } else {
            new CLIHomeManager().start();
        }    }

}
