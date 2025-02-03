package com.letsplay.graphicscontroller.gui;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.session.SessionManager;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class GUIProfile extends AbstractGUI {
    @FXML
    private Button notificationsButton;
    @FXML
    private Button profileButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField roleField;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;

    @FXML
    public void goBack(ActionEvent event) {
        goHome(event);
    }

    @FXML
    @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();

        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'profile.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'profile.fxml'.";
        assert backButton != null : "fx:id=backButton was not injected: check your FXML file 'profile.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'profile.fxml'.";
        this.nameField.setText(SessionManager.getInstance().getCurrentUser().getName());
        this.surnameField.setText(SessionManager.getInstance().getCurrentUser().getSurname());
        this.emailField.setText(SessionManager.getInstance().getCurrentUser().getEmail());
        this.usernameField.setText(SessionManager.getInstance().getCurrentUser().getUsername());
        this.roleField.setText(SessionManager.getInstance().getCurrentUser().getRole().getId());

    }
}
