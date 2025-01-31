package com.letsplay.graphicscontroller.gui;

import com.letsplay.controller.NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GUIHome extends NavigationController {

    @FXML
    public void login(ActionEvent event) {
        goToPage("login.fxml");
    }

    @FXML
    public void signup(ActionEvent event) {
        goToPage("signup.fxml");
    }
}
