module com.example.apex_grab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;

    opens com.example.apex_grab to javafx.fxml;
    exports com.example.apex_grab;
}