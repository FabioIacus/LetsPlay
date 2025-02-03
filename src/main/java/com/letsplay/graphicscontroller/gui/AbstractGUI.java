package com.letsplay.graphicscontroller.gui;

import com.letsplay.controller.JoinTournamentController;
import com.letsplay.controller.LoginController;
import com.letsplay.controller.NavigationController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.session.SessionManager;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

import static com.letsplay.model.domain.Role.CUSTOMER;

public class AbstractGUI extends NavigationController {
    JoinTournamentController joinTournamentController;

    @FXML
    protected void goHome(ActionEvent event) {
        if (SessionManager.getInstance().getCurrentUser().getRole() == CUSTOMER) {
            goToPage("homeCustomer.fxml");
        } else {
            goToPage("homeManager.fxml");
        }
    }

    @FXML
    protected void viewProfile(ActionEvent event) {
        goToPage("profile.fxml");
    }
    @FXML
    protected void viewNotifications(ActionEvent event) {
        if (SessionManager.getInstance().getCurrentUser().getRole() == CUSTOMER) {
            goToPage("notifications.fxml");
        } else {
            goToPage("requests.fxml");
        }
    }
    @FXML
    protected void logout(ActionEvent event) {
        new LoginController().logout();
        goToPage("homepage.fxml");
    }
    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();
    }
}
