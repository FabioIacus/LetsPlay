package com.letsplay.session;

import com.letsplay.GUIMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {
    private static Navigator instance = null;
    protected Stage stg;
    public Stage getStg() {return this.stg; }
    protected Navigator(Stage stg) {this.stg = stg;}

    public static Navigator getInstance(Stage stg){
        if(Navigator.instance == null)
            Navigator.instance = new Navigator(stg);
        return instance;
    }

    public static Navigator getInstance() {return instance;}

    public void goToPage(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(GUIMain.class.getResource(fxml)));
        stg.getScene().setRoot(pane);
    }

    public void goToWithController(String fxml, Object controller) throws IOException{
        FXMLLoader loader = new FXMLLoader(GUIMain.class.getResource(fxml));
        loader.setController(controller);
        Parent root = loader.load();
        stg.getScene().setRoot(root);
    }
}
