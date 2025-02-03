package com.letsplay.graphicscontroller.gui;

import com.letsplay.bean.SearchTournamentBean;
import com.letsplay.bean.SelectedTournamentBean;
import com.letsplay.bean.SimpleTournamentBean;
import com.letsplay.bean.TournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GUISelectTournament extends AbstractGUI {
    @FXML
    public Button backButton;
    @FXML
    public Button homeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button profileButton;
    @FXML
    public Text cityText;
    @FXML
    public TextField cityField;
    @FXML
    public TableView<SimpleTournamentBean> tableTournaments = new TableView<>();
    @FXML
    private TableColumn<SimpleTournamentBean, String> nameColumn;
    @FXML
    public TableColumn<SimpleTournamentBean, String> footballFacilityColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> addressColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> startDateColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> endDateColumn;
    @FXML
    private TableColumn<SimpleTournamentBean, String> typeColumn;

    ObservableList<SimpleTournamentBean> simpleTournamentBeanObservableList = FXCollections.observableArrayList();

    private List<SimpleTournamentBean> tournaments;
    private SearchTournamentBean city;
    public GUISelectTournament(List<SimpleTournamentBean> simpleTournamentBean, SearchTournamentBean searchTournamentBean) {
        this.tournaments = simpleTournamentBean;
        this.city = searchTournamentBean;
    }

    @FXML
    public void goBack(ActionEvent event) {
        goToPage("searchTournament.fxml");
    }

    @FXML
    public void viewDetails(MouseEvent event) throws SQLException, DatabaseException {
        SimpleTournamentBean selectedTournament = tableTournaments.getSelectionModel().getSelectedItem();
        if (selectedTournament != null) {
            SelectedTournamentBean selectedTournamentBean = new SelectedTournamentBean(selectedTournament.getName(), selectedTournament.getFootballFacility());
            TournamentBean tournamentBean = new JoinTournamentController().showDetails(selectedTournamentBean);
            goToWithController("tournamentDetails.fxml", new GUIViewDetails(tournamentBean, tournaments, city));
        }
    }


    @FXML  @Override
    public void initialize() throws DAOException, CsvValidationException, SQLException, IOException, DatabaseException {
        super.initialize();
        joinTournamentController = new JoinTournamentController();


        assert backButton != null : "fx:id=backButton was not injected: check your FXML file 'selectTournament.fxml'.";
        assert homeButton != null : "fx:id=homeButton was not injected: check your FXML file 'selectTournament.fxml'.";
        assert notificationsButton != null : "fx:id=notificationsButton was not injected: check your FXML file 'selectTournament.fxml'.";
        assert profileButton != null : "fx:id=profileButton was not injected: check your FXML file 'selectTournament.fxml'.";
        assert nameColumn != null : "fx:id=nameColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert footballFacilityColumn != null : "fx:id=footballFacilityColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert addressColumn != null : "fx:id=addressColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert startDateColumn != null : "fx:id=startDateColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert endDateColumn != null : "fx:id=endDateColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert typeColumn != null : "fx:id=typeColumn was not injected: check your FXML file 'selectTournament.fxml'.";
        assert tableTournaments != null : "fx:id=tableTournaments was not injected: check your FXML file 'selectTournament.fxml'.";


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        footballFacilityColumn.setCellValueFactory(new PropertyValueFactory<>("footballFacility"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        simpleTournamentBeanObservableList.addAll(tournaments);
        tableTournaments.setItems(simpleTournamentBeanObservableList);
    }
}
