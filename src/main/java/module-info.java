module com.example.loginassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.loginassignment to javafx.fxml;
    exports com.example.loginassignment;
}