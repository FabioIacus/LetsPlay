module com.letsplay.letsplay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.letsplay to javafx.fxml;
    exports com.letsplay;
    exports com.letsplay.controller;
    opens com.letsplay.controller to javafx.fxml;
}