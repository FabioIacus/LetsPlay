package com.letsplay;

import com.letsplay.session.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIMain.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Navigator n = Navigator.getInstance(stage);

        stage.setTitle("Let's Play!");
        n.getStg().setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}