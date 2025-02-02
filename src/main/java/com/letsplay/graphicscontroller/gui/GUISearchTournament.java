package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.InputException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUISearchTournament extends AbstractGUI {
    @FXML
    public Button backButton;
    @FXML
    public Text cityText;
    @FXML
    public TextField cityField;
    @FXML
    public Button searchButton;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;

    @FXML
    public void searchTournaments() {
        try {
            String city = cityField.getText();
            SearchTournamentBean searchTournamentBean = new SearchTournamentBean(city);
            List<SimpleTournamentBean> simpleTournamentBean = joinTournamentController.searchTournaments(searchTournamentBean);
            if (simpleTournamentBean.isEmpty()) {
                showErrorAlert("Join tournament", "", "No tournament found, choose another city!");
            } else {
                goToWithController("selectTournament.fxml", new GUISelectTournament(simpleTournamentBean, searchTournamentBean));
            }
        } catch(InputException | SQLException | DatabaseException e) {
            showErrorAlert("Join tournament", "", e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        goHome(actionEvent);
    }

    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();


        assert backButton != null : "fx:id=backButton was not injected: check your FXML file 'searchTournament.fxml'.";
        assert cityText != null : "fx:id=cityText was not injected: check your FXML file 'searchTournament.fxml'.";
        assert cityField != null : "fx:id=cityField was not injected: check your FXML file 'searchTournament.fxml'.";
        assert searchButton != null : "fx:id=searchButton was not injected: check your FXML file 'searchTournament.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'searchTournament.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'searchTournament.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'searchTournament.fxml'.";


    }

}
