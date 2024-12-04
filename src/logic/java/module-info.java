module com.letsplay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.letsplay to javafx.fxml;
    exports com.letsplay;
    exports com.letsplay.controller;
    exports com.letsplay.bean;
    exports com.letsplay.exception;
    exports com.letsplay.graphicscontroller.cli;
    exports com.letsplay.graphicscontroller.gui;
    exports com.letsplay.model.dao;
    exports com.letsplay.model.domain;

    opens com.letsplay.controller to javafx.fxml;
}