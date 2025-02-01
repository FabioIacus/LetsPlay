package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUIRequests extends AbstractGUI {
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button backButton;
    @FXML
    public ListView<String> listRequests;

    @FXML
    public void viewProfile(ActionEvent event) {
        goToPage("profile.fxml");
    }
    @FXML
    public void goBack(ActionEvent event) {
        goHome(event);
    }

    @FXML
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        try {
            joinTournamentController = new JoinTournamentController();
            List<RegistrationBean> registrationBeanList = new JoinTournamentController().getRequests();
            // Creazione della lista osservabile
            ObservableList<String> items = FXCollections.observableArrayList();
            int i = 1;
            for (RegistrationBean bean : registrationBeanList) {
                String formattedText = i + "    Tournament: " + bean.getTournament() +
                        "\n      Team: " + bean.getTeam() +
                        "\n      Number of players: " + bean.getNumPlayers() +
                        "\n      Captain name: " + bean.getCaptain() +
                        "\n      Customer email: " + bean.getCustomerEmail();

                items.add(formattedText);
                i++;
            }
            // Impostare la lista sulla ListView
            listRequests.setItems(items);
        } catch (SQLException | CsvValidationException | IOException | DatabaseException e) {
            showErrorAlert("Notifications", "", "No requests found!");
        }
    }
}
