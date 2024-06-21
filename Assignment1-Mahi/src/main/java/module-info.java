module com.example.assignment1neel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.assignment1mahi to javafx.fxml;
    exports com.example.assignment1mahi;
}