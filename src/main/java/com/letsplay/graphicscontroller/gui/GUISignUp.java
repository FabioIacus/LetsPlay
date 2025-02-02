package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.NavigationController;
import com.letsplay.controller.SignUpController;
import com.letsplay.exception.*;
import com.letsplay.model.domain.Role;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class GUISignUp extends NavigationController {

    private SignUpController signUpController;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> roleBox;
    @FXML
    private Button signupButton;


    @FXML
    public void signup(ActionEvent event) {
        String roleStr = roleBox.getValue();
        Role role;
        try {
            if (roleStr.equals("Customer")) {
                role = Role.CUSTOMER;
            }
            else if (roleStr.equals("Manager")) {
                role = Role.MANAGER;
            }
            else {
                throw new InputException("Invalid role!");
            }
            UserBean userBean = new UserBean(nameField.getText(), surnameField.getText(), emailField.getText(), passwordField.getText(),
                    usernameField.getText(), role.getId());
            int registration = signUpController.signUp(userBean);
            if (registration == 0) {
                logger.log(Level.INFO, "Registration successful!");
                showInfoAlert("Registration", "Registration successful!", "You can now log in to the application.");
                goToPage("homepage.fxml");
            }
        } catch (UsernameException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("Credential error", "Username already exists!", "Invalid username!");
        } catch (InputException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("Registration error", "Role not found", "Invalid role!");
        }
        catch (EmailException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("Registration error", "Email already exists!", "Invalid email!");
        }
        catch (DatabaseException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("Database error", "", "");
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        goToPage("homepage.fxml");
    }

    @FXML
    @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        signUpController = new SignUpController();

        assert signupButton != null : "fx:id=loginButton was not injected: check your FXML file 'signup.fxml'.";
        assert passwordField != null : "fx:id=passwordField was not injected: check your FXML file 'signup.fxml'.";
        assert emailField != null : "fx:id=emailField was not injected: check your FXML file 'signup.fxml'.";
        assert nameField != null : "fx:id=nameField was not injected: check your FXML file 'signup.fxml'.";
        assert surnameField != null : "fx:id=surnameField was not injected: check your FXML file 'signup.fxml'.";
        assert usernameField != null : "fx:id=usernameField was not injected: check your FXML file 'signup.fxml'.";
    }
}
