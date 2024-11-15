module com.letsplay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.letsplay to javafx.fxml;
    exports com.letsplay;
    exports com.letsplay.controller;
    exports com.letsplay.bean;
    exports com.letsplay.exception;
    exports com.letsplay.graphic_controller.CLI;
    exports com.letsplay.graphic_controller.GUI;
    exports com.letsplay.model.DAO;
    exports com.letsplay.model.domain;

    opens com.letsplay.controller to javafx.fxml;
}