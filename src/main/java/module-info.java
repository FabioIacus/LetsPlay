module com.letsplay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;
    requires com.opencsv;
    requires mysql.connector.j;
    requires jdk.compiler;


    opens com.letsplay to javafx.fxml;
    exports com.letsplay;
    exports com.letsplay.controller;
    exports com.letsplay.bean;
    exports com.letsplay.exception;
    exports com.letsplay.graphicscontroller.cli;
    exports com.letsplay.graphicscontroller.gui;
    exports com.letsplay.model.dao;
    exports com.letsplay.model.dao.queries;
    exports com.letsplay.model.domain;
    exports com.letsplay.notification;

    opens com.letsplay.controller to javafx.fxml;
    exports com.letsplay.session;
}