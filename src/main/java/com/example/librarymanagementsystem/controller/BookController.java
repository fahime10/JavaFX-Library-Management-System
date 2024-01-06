package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class BookController {
    @FXML
    private ImageView addBookButton;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        addBookButton.setOnMousePressed(event -> {

        });
    }
}
