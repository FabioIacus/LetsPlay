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
    @FXML
    private Button selectionMessagesButton;

    @FXML
    private Button selectionRequestButton;
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
    @FXML
    protected void selectedViewMessages(ActionEvent event) {
        goToPage("notifications.fxml");
    }

    @FXML
    protected void selectedViewRequests(ActionEvent event) {
        goToPage("requests.fxml");
    }
    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert selectionMessagesButton != null : "fx:id=\"SelectionMessagesButton\" was not injected: check your FXML file 'selectionMessageOrRequests.fxml'.";
        assert selectionRequestButton != null : "fx:id=\"SelectionRequestButton\" was not injected: check your FXML file 'selectionMessageOrRequests.fxml'.";
    }
}
