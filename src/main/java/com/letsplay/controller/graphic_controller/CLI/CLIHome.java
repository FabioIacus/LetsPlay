package com.letsplay.controller.graphic_controller.CLI;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.LoginController;
import com.letsplay.exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;

public class CLIHome extends AbstractCLI{

    public void start() throws InvalidInputException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                    case 1 -> login();
                   // case 2 -> new CLISignUpGraphicController().start();
                    case 3 -> System.exit(0);
                    default -> throw new InvalidInputException("Invalid choice");
                }
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
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

    private void login() {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Username: ");
            String username = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();
            UserBean bean = new UserBean(username, password);
            loginController.login(bean);

            new CLIHomeGraphicController().start();
        } catch (InvalidFormatException | DAOException | IOException | SQLException e) {
            logger.log(Level.INFO, e.getMessage());
            start();
        }
    }
}
