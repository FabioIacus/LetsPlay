package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.*;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.EmailException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public ListView<RegistrationBean> listRequests;
    @FXML
    public final String TITLE = "Notifications";

    @FXML
    public void acceptOrReject(MouseEvent event) {
        event.consume();
        int choice;
        try {
            RegistrationBean selectedRequest = listRequests.getSelectionModel().getSelectedItem();
            // Se nessun elemento è selezionato, esci dalla funzione
            if (selectedRequest == null) {
                return;
            }
            Optional<UserResponse> userResponse = showAcceptOrRejectAlert(TITLE, "Accept or reject request",
                    "Enter a message:");
            if (userResponse.isEmpty()) {
                return;
            }
            UserResponse response = userResponse.get();
            choice = response.isAccepted() ? 1 : 0;
            String message = response.getMessage();
            ResponseBean responseBean = new ResponseBean(choice, message);
            new JoinTournamentController().manageRequest(selectedRequest, responseBean);
            showConfirmationAlert(TITLE, "", "Request processed successfully!");
            goToPage("homeManager.fxml");
        } catch (SQLException | IOException | CsvException e) {
            showErrorAlert(TITLE, "", e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        goHome(event);
    }

    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();


        try {
            List<RegistrationBean> registrationBeanList = new JoinTournamentController().getRequests();
            // Creazione della lista osservabile
            ObservableList<RegistrationBean> items = FXCollections.observableArrayList(registrationBeanList);
            // Impostare la lista sulla ListView
            listRequests.setItems(items);
            // Personalizza la visualizzazione della ListView
            listRequests.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(RegistrationBean bean, boolean empty) {
                    super.updateItem(bean, empty);
                    if (empty || bean == null) {
                        setText(null);
                    } else {
                        int index = getIndex() + 1;
                        setText(index + ".    Tournament: " + bean.getTournament() +
                                "\n       Team: " + bean.getTeam() +
                                "\n       Number of players: " + bean.getNumPlayers() +
                                "\n       Captain name: " + bean.getCaptain() +
                                "\n       Customer email: " + bean.getCustomerEmail());
                    }
                }
            });
        } catch (SQLException | CsvValidationException | IOException | DatabaseException e) {
            showErrorAlert(TITLE, "", "No requests found!");
        }
    }
}