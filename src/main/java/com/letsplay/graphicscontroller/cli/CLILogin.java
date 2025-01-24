package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.LoginController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.InputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CLILogin {

    public void start() throws InputException {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();

            //passaggio delle credenziali per il login
            UserBean userBean = new UserBean(email, password);
            Object[] result = loginController.login(userBean);

            //login eseguito correttamente
            System.out.println("-- Hi " + result[0] + "! --");
            //accesso pagina iniziale
            if (result[1].equals("CUSTOMER")) {
                new CLIHomeCustomer().start();
            } else {
                new CLIHomeManager().start();
            }
        } catch (InputException | IOException | SQLException | DAOException e) {
            System.out.println(e.getMessage());
            new CLIHomePage().start();
        }

    }

}
