package com.letsplay.graphicscontroller.gui;

import com.letsplay.controller.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GUIHomeCustomer extends NavigationController {
    @FXML
    private Button logoutButton;

    @FXML
    private ImageView messageButton;

    @FXML
    private Button searchButton;
    @FXML
    private ImageView profile;
    @FXML
    private TextField textField;

    /*@FXML
    void goProfile(MouseEvent event) {
        goToPage("profileInfo.fxml");
    }
    @FXML
    public void search(ActionEvent event) {
        try {
            TourSearchBean bean = new TourSearchBean(textField.getText());
            List<TourBean> listOfTourBeans = joinTourController.findTourOfCity(bean);
            if (!listOfTourBeans.isEmpty())
                goToWithController("selectTour.fxml", new SelectTourGraphicController(listOfTourBeans));
            else
                showInfoAlert("No tour available","There are no tours for this city","Choose another city");

        } catch (InvalidFormatException e) {
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("City error","Invalid format","City is in an invalid format");
        }
        catch (SQLException e){
            logger.log(Level.INFO, e.getMessage());
            showErrorAlert("DB error","","");
        }
    }


    @FXML @Override
    public void initialize() {
        super.initialize();
        joinTournamentController = new JoinTournamentController();


        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'home.fxml'.";
        assert messageButton != null : "fx:id=\"messageButton\" was not injected: check your FXML file 'home.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'home.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'home.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'home.fxml'.";

    }*/

}
