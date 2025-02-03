package com.letsplay.graphicscontroller.gui;

import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class GUIHomeManager extends AbstractGUI {
    @FXML
    public Button logoutButton;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button createTournamentButton;
    @FXML
    public Button deleteTournamentButton;
    @FXML
    public Button editTournamentButton;
    @FXML
    public final static String notImplemented = "This feature has not been implemented yet!";

    @FXML
    public void createTournament(ActionEvent event) {
        showErrorAlert("Create tournament", "It is not possible to create a tournament!", notImplemented);
    }
    @FXML
    public void deleteTournament(ActionEvent event) {
        showErrorAlert("Delete tournament", "It is not possible to delete a tournament!", notImplemented);
    }
    @FXML
    public void editTournament(ActionEvent event) {
        showErrorAlert("Edit tournament", "It is not possible to edit a tournament!", notImplemented);
    }

    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert createTournamentButton != null : "fx:id=createTournamentButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert deleteTournamentButton != null : "fx:id=deleteTournamentButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert editTournamentButton != null : "fx:id=editTournamentButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'homeManager.fxml'.";
        assert logoutButton != null : "fx:id=logoutButton was not injected: check your FXML file 'homeManager.fxml'.";
    }
}
