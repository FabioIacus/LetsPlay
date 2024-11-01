package com.letsplay.controller.graphic_controller.CLI;

import java.io.IOException;
import java.util.logging.Level;

public class CLIHome extends AbstractCLI{

    public void start() throws IOException {
        while (true) {
            int choice;
            try {
                choice = showMenu();
                switch (choice) {
                   // case 1 ->  login();
                   // case 2 -> new CLISignUpGraphicController().start();
                    case 3 -> System.exit(0);
                    default -> throw new IOException("Invalid choice");
                }
            } catch (IOException e) {
               // logger.log(Level.INFO, e.getMessage());
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
}
