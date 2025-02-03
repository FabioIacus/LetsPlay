package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.bean.TournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUIViewDetails extends AbstractGUI {
    @FXML
    public Button backButton;
    @FXML
    public Text nameText;
    @FXML
    public Text footballFacilityText;
    @FXML
    public Text cityText;
    @FXML
    public Text addressText;
    @FXML
    public Text startDateText;
    @FXML
    public Text endDateText;
    @FXML
    public Text participationFeeText;
    @FXML
    public Text numberText;
    @FXML
    public Text prizeText;
    @FXML
    public Text requirementsText;
    @FXML
    public Text typeText;
    @FXML
    public Text managerEmailText;
    @FXML
    public Button signupButton;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;

    private TournamentBean tournament;
    private List<SimpleTournamentBean> tournaments;
    private SearchTournamentBean city;
    public GUIViewDetails(TournamentBean tournament, List<SimpleTournamentBean> tournaments, SearchTournamentBean searchTournamentBean) {
        this.tournament = tournament;
        this.tournaments = tournaments;
        this.city = searchTournamentBean;
    }

    @FXML
    public void goBack(ActionEvent event) {
        goToWithController("selectTournament.fxml", new GUISelectTournament(tournaments, city));
    }

    @FXML
    public void signUp(ActionEvent event) {
        goToWithController("tournamentSignUp.fxml", new GUITournamentSignUP(tournament, tournaments, city));
    }

    @FXML
    @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert backButton != null : "fx:id=backButton was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert nameText != null : "fx:id=nameText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert footballFacilityText != null : "fx:id=footballFacilityText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert cityText != null : "fx:id=cityText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert addressText != null : "fx:id=addressText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert startDateText != null : "fx:id=startDateText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert endDateText != null : "fx:id=endDateText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert participationFeeText != null : "fx:id=participationFeeText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert numberText != null : "fx:id=numberText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert prizeText != null : "fx:id=prizeText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert requirementsText != null : "fx:id=requirementsText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert typeText != null : "fx:id=typeText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert managerEmailText != null : "fx:id=managerEmailText was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert signupButton != null : "fx:id=signupButton was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'tournamentDetails.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'tournamentDetails.fxml'.";

        this.nameText.setText("Tournament: " + tournament.getName());
        this.footballFacilityText.setText("Football facility: " + tournament.getFootballFacility());
        this.cityText.setText("City: " + tournament.getCity());
        this.addressText.setText("Address: " + tournament.getAddress());
        this.startDateText.setText("Start date: " + tournament.getStartDate());
        this.endDateText.setText("End date: " + tournament.getEndDate());
        this.participationFeeText.setText("Participation fee: " + tournament.getParticipationFee() + "€");
        this.numberText.setText("Total number of teams: " + tournament.getNumTeams());
        this.prizeText.setText("Prize for the winning team: " + tournament.getPrize());
        this.requirementsText.setText("Requirements: " + tournament.getRequirements());
        this.typeText.setText("Type: " + tournament.getType());
        this.managerEmailText.setText("Manager email: " + tournament.getManagerEmail());
    }
}
