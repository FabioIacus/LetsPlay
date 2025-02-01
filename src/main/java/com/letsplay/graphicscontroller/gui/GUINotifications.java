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

public class GUINotifications extends AbstractGUI {
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button backButton;
    @FXML
    public ListView<String> listNotifications;
    private ObservableList<String> items;


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
            List<RegistrationBean> registrationBeanList = new JoinTournamentController().getResponses();
            // Creazione della lista osservabile
            items = FXCollections.observableArrayList();
            String message;
            int i = 1;
            for (RegistrationBean bean : registrationBeanList) {
                if (bean.getMessage() == null || bean.getMessage().isEmpty() || bean.getMessage().isBlank()) {
                    message = "No message sent by manager";
                } else {
                    message = bean.getMessage();
                }
                String formattedText = i + "    Tournament: " + bean.getTournament() +
                        "\n      Registered team: " + bean.getTeam() +
                        "\n      Number of players: " + bean.getNumPlayers() +
                        "\n      Captain name: " + bean.getCaptain() +
                        "\n      Manager email: " + bean.getManagerEmail() +
                        "\n      Request status: " + bean.getStatus() +
                        "\n      Message: " + message;

                items.add(formattedText);
                i++;
            }
            // Impostare la lista sulla ListView
            listNotifications.setItems(items);
        } catch (SQLException | DAOException | CsvValidationException | IOException | DatabaseException e) {
            showErrorAlert("Notifications", "", "No requests found!");
        }
    }
}
