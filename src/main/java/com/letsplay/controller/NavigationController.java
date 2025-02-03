package com.letsplay.controller;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.session.Navigator;
import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationController {

    protected Alert errorAlert;
    protected Alert infoAlert;
    protected Alert confirmationAlert;
    protected Logger logger = Logger.getAnonymousLogger();

    @FXML
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    }

    protected void showInfoAlert(String title, String header, String content){
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(header);
        infoAlert.setContentText(content);
        infoAlert.show();
    }
    protected void showErrorAlert(String title, String header, String content){
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.show();
    }
    protected ButtonType showConfirmationAlert(String title, String header, String content){
        confirmationAlert.setTitle(title);
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        confirmationAlert.getButtonTypes().setAll(ButtonType.OK);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isEmpty()) {
            throw new InputMismatchException();
        }
        return result.get();
    }

    public static class UserResponse {
        private final boolean accepted;
        private final String message;

        public UserResponse(boolean accepted, String message) {
            this.accepted = accepted;
            this.message = message;
        }

        public boolean isAccepted() {
            return accepted;
        }

        public String getMessage() {
            return message;
        }
    }
    protected Optional<UserResponse> showAcceptOrRejectAlert(String title, String header, String content) {
        Dialog<UserResponse> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        TextArea inputField = new TextArea();
        inputField.setPromptText("Type your message here...");
        inputField.setWrapText(true);

        VBox vbox = new VBox(new Label(content), inputField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType acceptButton = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType rejectButton = new ButtonType("Reject", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(acceptButton, rejectButton);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButton) {
                return new UserResponse(true, inputField.getText().trim());
            } else if (dialogButton == rejectButton) {
                return new UserResponse(false, inputField.getText().trim());
            }
            return null;
        });
        return dialog.showAndWait();
    }

    protected void goToPage(String page){
        try{
            Navigator.getInstance().goToPage(page);
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
    protected void goToWithController(String page, Object controller){
        try {
            Navigator.getInstance().goToWithController(page, controller);
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
