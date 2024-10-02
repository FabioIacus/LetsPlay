module com.letsplay.letsplay {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.letsplay to javafx.fxml;
    exports com.letsplay;
}