package com.letsplay.graphicscontroller.gui;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class GUIHomeCustomer extends AbstractGUI {
    @FXML
    private Button reservePitchButton;
    @FXML
    private Button joinTournamentButton;
    @FXML
    private Button sharedMatchButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button notificationsButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button logoutButton;

    @FXML
    public void reservePitch(ActionEvent event) {
        showErrorAlert("Reserve a pitch", "It is not possible to reserve a pitch!", "This feature has not been implemented yet!");
    }
    @FXML
    public void joinTournament(ActionEvent event) {
        goToPage("selectTournament.fxml");
    }
    @FXML
    public void joinSharedMatch(ActionEvent event) {
        showErrorAlert("Join shared match", "It is not possible to join a shared match!", "This feature has not been implemented yet!");
    }
    @FXML
    public void viewProfile(ActionEvent event) {
        goToPage("profile.fxml");
    }


    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();

        assert reservePitchButton != null : "fx:id=reservePitchButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert joinTournamentButton != null : "fx:id=joinTournamentButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert sharedMatchButton != null : "fx:id=sharedMatchButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'homeCustomer.fxml'.";
        assert logoutButton != null : "fx:id=logoutButton was not injected: check your FXML file 'homeCustomer.fxml'.";
    }

}