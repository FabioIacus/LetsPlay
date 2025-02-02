package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUISelectTournament2 extends AbstractGUI {
    @FXML
    public Button backButton;
    @FXML
    public Text cityText;
    @FXML
    public TextField cityField;
    @FXML
    public TableView<SimpleTournamentBean> tableTournaments;
    @FXML
    private TableColumn<SimpleTournamentBean, String> nameColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> facilityColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> addressColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> startDateColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> endDateColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> typeColumn;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;
    @FXML
    private List<SimpleTournamentBean> tournaments;
    @FXML
    private SearchTournamentBean city;
    public GUISelectTournament2(List<SimpleTournamentBean> tournamentsList, SearchTournamentBean city) {
        this.tournaments = tournamentsList;
        this.city = city;

    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        goToPage("searchTournament.fxml");
    }

    @FXML @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();

        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'selectTour.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'selectTour.fxml'.";
        assert notificationsButton != null : "fx:id=\"messageButton\" was not injected: check your FXML file 'selectTour.fxml'.";
        assert profileButton != null : "fx:id=\"tourName\" was not injected: check your FXML file 'selectTour.fxml'.";
        assert cityField != null : "fx:id=\"tourPrice\" was not injected: check your FXML file 'selectTour.fxml'.";
        assert tableTournaments != null : "fx:id=\"tourTable\" was not injected: check your FXML file 'selectTour.fxml'.";

        this.cityField.setText(city.getCity());
        // Associa ogni colonna con il campo della SimpleTournamentBean
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        facilityColumn.setCellValueFactory(new PropertyValueFactory<>("footballFacility"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Se i tornei sono già stati impostati, aggiorna la tabella
        if (tournaments != null) {
            tableTournaments.getItems().setAll(tournaments);
        }
    }
}
