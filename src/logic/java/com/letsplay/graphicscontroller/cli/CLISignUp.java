package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.SignUpController;
import com.letsplay.exception.InputException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.model.domain.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLISignUp {
    public void start() {
        SignUpController signUpController = new SignUpController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Role role;
        try {
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

            if(roleStr.equals("customer")) {
                role = Role.CUSTOMER;
            }
            else if (roleStr.equals("manager")) {
                role = Role.MANAGER;
            }
            else {
                throw new InputException("Invalid role!");
            }

            UserBean userBean = new UserBean(username, name, surname, email, password, role.getId());
            int registration = signUpController.signUp(userBean);
            if (registration == 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed!");
            }
        } catch (IOException | InputException | DatabaseException e) {
            System.out.println(e.getMessage());
            start();
        }
    }
}
