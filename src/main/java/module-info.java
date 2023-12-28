module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires mysql.connector.j;

    opens com.example.librarymanagementsystem to javafx.fxml;
    exports com.example.librarymanagementsystem;
    exports com.example.librarymanagementsystem.controller;
    opens com.example.librarymanagementsystem.controller to javafx.fxml;
}