package com.letsplay.controller.graphic_controller.CLI;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.LoginController;
import com.letsplay.exception.Exception1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class CLIHome extends AbstractCLI{

    public void start() throws Exception1 {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> login();
                   // case 2 -> new CLISignUpGraphicController().start();
                    case 3 -> System.exit(0);
                   // default -> throw new Exception1("PORCODIO");
                }
            } catch (InputMismatchException e) {
                throw new Exception1("You have entered an incorrect input");
            }
        }
    }

    private int showMenu() {
        System.out.println("---Menu---");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");

        return getChoice(1, 3);
    }

    private void login() throws Exception1 {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();
            UserBean login = new UserBean(email, password);
            loginController.login(login);
            new CLIHomeCustomer().start();
        } catch (Exception1 | IOException e) {
            System.out.println(e.getMessage());
            start();
        }
    }
}
