package com.letsplay.graphic_controller.CLI;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.LoginController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.Exception1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CLILogin {

    public void start() throws Exception1 {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();

            //passaggio delle credenziali per il login
            UserBean userBean = new UserBean(email, password);
            loginController.login(userBean);

            //accesso pagina iniziale
            new CLIHomeCustomer().start();

        } catch (Exception1 | IOException | SQLException | DAOException e) {
            System.out.println(e.getMessage());
            start();
        }
    }

}
