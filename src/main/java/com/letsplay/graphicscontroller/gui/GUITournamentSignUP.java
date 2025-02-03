package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.bean.TournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.*;
import com.letsplay.session.SessionManager;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUITournamentSignUP extends AbstractGUI {
    @FXML
    public Button backButton;
    @FXML
    public Text teamNameText;
    @FXML
    public Text numberPlayersText;
    @FXML
    public Text captainNameText;
    @FXML
    public Button submitButton;
    @FXML
    public TextField teamNameField;
    @FXML
    public TextField numberPlayersField;
    @FXML
    public TextField captainNameField;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;

    private TournamentBean selectedTournament;
    private List<SimpleTournamentBean> tournaments;
    private SearchTournamentBean city;
    public GUITournamentSignUP(TournamentBean selectedTournament, List<SimpleTournamentBean> tournaments, SearchTournamentBean city) {
        this.selectedTournament = selectedTournament;
        this.tournaments = tournaments;
        this.city = city;
    }

    @FXML
    public void submit(ActionEvent event) {
        try {
            RegistrationBean registrationBean = new RegistrationBean(
                    SessionManager.getInstance().getCurrentUser().getEmail(),
                    selectedTournament.getManagerEmail(),
                    selectedTournament.getName()
            );
            // Controllo campi vuoti PRIMA di eseguire il parse
            if (teamNameField.getText().isEmpty() ||
                    numberPlayersField.getText().isEmpty() ||
                    captainNameField.getText().isEmpty() ||
                    teamNameField.getText().isBlank() ||
                    numberPlayersField.getText().isBlank() ||
                    captainNameField.getText().isBlank()) {
                throw new EmptyFieldsException("There are empty fields!");
            }
            registrationBean.setTeam(teamNameField.getText());
            String type = selectedTournament.getType();
            int numTeamPlayers = Integer.parseInt(numberPlayersField.getText());
            int min = switch (type) {
                case "Calcio a 5" -> 5;
                case "Calcio a 8" -> 8;
                case "Calcio a 11" -> 11;
                default -> 0;
            };
            if (numTeamPlayers < min) {
                throw new IOException("The minimum number of players is " + min + "!");
            }
            if (numTeamPlayers > 20) {
                throw new IOException("The maximum number of players per team is 20!");
            }
            registrationBean.setNumPlayers(numTeamPlayers);
            registrationBean.setCaptain(captainNameField.getText());
            new JoinTournamentController().signUpTeam(registrationBean);
            showConfirmationAlert("Join tournament", "", "Request sent successfully!");
            goToPage("homeCustomer.fxml");
        } catch (EmptyFieldsException | IOException | SQLException | CsvException | EmailException | NumberFormatException e) {
            showErrorAlert("Join tournament", "", e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        goToWithController("tournamentDetails.fxml", new GUIViewDetails(selectedTournament, tournaments, city));
    }

    @FXML
    @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert backButton != null : "fx:id=backButton was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert teamNameText != null : "fx:id=teamNameText was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert numberPlayersText != null : "fx:id=numberPlayersText was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert captainNameText != null : "fx:id=captainNameText was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert submitButton != null : "fx:id=submitButton was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert teamNameField != null : "fx:id=teamNameField was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert numberPlayersField != null : "fx:id=numberPlayersField was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert captainNameField != null : "fx:id=captainNameField was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'tournamentSignUp.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'tournamentSignUp.fxml'.";

    }

    }
