package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.SignUpController;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.InputException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.UsernameException;
import com.letsplay.model.domain.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLISignUp {
    public void start() {
        SignUpController signUpController = new SignUpController();
        Role role;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Name: ");
            String name = reader.readLine();
            System.out.print("Surname: ");
            String surname = reader.readLine();
            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();
            System.out.print("Username: ");
            String username = reader.readLine();
            System.out.print("Role (customer or manager): ");
            String roleStr = reader.readLine().toLowerCase();
            //settaggio ruolo
            if (roleStr.equals("customer")) {
                role = Role.CUSTOMER;
            }
            else if (roleStr.equals("manager")) {
                role = Role.MANAGER;
            }
            else {
                throw new InputException("Invalid role!");
            }
            //creazione bean
            UserBean userBean = new UserBean(name, surname, email, password, username, role.getId());
            int registration = signUpController.signUp(userBean);
            if (registration == 0) {
                System.out.println("Registration successful!");
            }
        } catch (IOException | InputException | DatabaseException | EmailException | UsernameException e) {
            System.out.println(e.getMessage());
            new CLIHomePage().start();
        }
    }
}
