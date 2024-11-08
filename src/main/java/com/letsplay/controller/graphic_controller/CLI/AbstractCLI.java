package com.letsplay.controller.graphic_controller.CLI;

import java.util.Scanner;

public class AbstractCLI {

    protected int getChoice(int min, int max) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= min && choice <= max) {
                break;
            }
            System.out.println("Invalid option\n");
        }
        return choice;
    }

    protected void logout(){
        //new LoginController().logout();
        //new CLILoginGraphicController().start();
    }

}
