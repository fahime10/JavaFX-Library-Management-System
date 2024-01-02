package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private Button backButton;

    @FXML
    private TextField signupFirstName;

    @FXML
    private TextField signupLastName;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Button signupSubmitButton;

    @FXML
    private TextField signupUsername;

    @FXML
    void initialize() {
        signupSubmitButton.setOnAction(event -> {
            createUser();

            switchScene("/com/example/librarymanagementsystem/login.fxml");
        });

        backButton.setOnAction(event -> {
            switchScene("/com/example/librarymanagementsystem/login.fxml");
        });
    }

    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signupFirstName.getText();
        String lastName = signupLastName.getText();
        String username = signupUsername.getText();
        String password = signupPassword.getText();

        User user = new User(firstName, lastName, username, password);

        databaseHandler.addUser(user);
    }

    private void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage currentStage = (Stage) signupSubmitButton.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
