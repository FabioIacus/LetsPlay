package com.letsplay.graphicscontroller.gui;

import com.letsplay.controller.JoinTournamentController;
import com.letsplay.controller.LoginController;
import com.letsplay.controller.NavigationController;
import com.letsplay.session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import static com.letsplay.model.domain.Role.CUSTOMER;
import static com.letsplay.model.domain.Role.MANAGER;

public class AbstractGUI extends NavigationController {
    @FXML
    private Button selectionMessagesButton;

    @FXML
    private Button selectionRequestButton;
    JoinTournamentController joinTournamentController;

    @FXML
    protected void goMessage(MouseEvent event) {
        if (SessionManager.getInstance().getCurrentUser().getRole() == CUSTOMER) {
            goToPage("notifications.fxml");
        } else if(SessionManager.getInstance().getCurrentUser().getRole() == MANAGER){
            goToPage("selectionMessageOrRequests.fxml");
        }
    }
    @FXML
    protected void logout(ActionEvent event) {
        new LoginController().logout();
        goToPage("homepage.fxml");
    }
    @FXML
    protected void goHome(MouseEvent event) {
        goToPage("home.fxml");
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
    public void initialize() {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert selectionMessagesButton != null : "fx:id=\"SelectionMessagesButton\" was not injected: check your FXML file 'selectionMessageOrRequests.fxml'.";
        assert selectionRequestButton != null : "fx:id=\"SelectionRequestButton\" was not injected: check your FXML file 'selectionMessageOrRequests.fxml'.";
    }
}
