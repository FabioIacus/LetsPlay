package com.letsplay.graphicscontroller.cli;

import com.letsplay.controller.LoginController;
import com.letsplay.exception.InputException;

import java.util.Scanner;

public abstract class AbstractCLI {

    protected int getChoice(int min, int max) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            System.out.println("Invalid option. You must enter a number between " + min + " and " + max + ".");
        }
        return choice;
    }

    protected void logout() throws InputException {
        new LoginController().logout();
        System.out.println("You are about to be logged out...");
        new CLIHome().start();
    }

}
