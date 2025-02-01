package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.UserBean;
import com.letsplay.controller.LoginController;
import com.letsplay.controller.NavigationController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class GUILogin extends NavigationController {
    @FXML
    public Button backButton;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    private LoginController loginController;

    @FXML
    public void login(ActionEvent event) {
        try {
            UserBean credentials = new UserBean(emailField.getText(), passwordField.getText());
            Object[] result = loginController.login(credentials);
            if (result[1].equals("CUSTOMER")) {
                goToPage("homeCustomer.fxml");
            } else {
                goToPage("homeManager.fxml");
            }
        } catch (DAOException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("Credential error", "User not found", "Incorrect email or password!");
        } catch (SQLException e) {
            logger.log(Level.INFO, e.getMessage());
            showInfoAlert("Database error!", "", "");
        }
    }

    public void goBack(ActionEvent event) {
        goToPage("homepage.fxml");
    }


    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        loginController = new LoginController();

        assert loginButton != null : "fx:id=loginButton was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=passwordField was not injected: check your FXML file 'login.fxml'.";
        assert emailField != null : "fx:id=emailField was not injected: check your FXML file 'login.fxml'.";

    }
}
