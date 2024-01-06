package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private AnchorPane window;
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
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        signupSubmitButton.setOnAction(event -> {
            boolean successful = createUser();

            if (successful) {
                sceneSwitcher.switchScene(signupSubmitButton, "/com/example/librarymanagementsystem/login.fxml");
            }
        });

        backButton.setOnAction(event -> {
            sceneSwitcher.switchScene(signupSubmitButton, "/com/example/librarymanagementsystem/login.fxml");
        });
    }

    private boolean createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Shaker shaker = new Shaker(window);
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        boolean nonNull = false;

        if (signupFirstName.getText() != "" && signupLastName.getText() != ""  &&
            signupUsername.getText() != "" && signupPassword.getText() != "") {

            String firstName = signupFirstName.getText();
            String lastName = signupLastName.getText();
            String username = signupUsername.getText();
            String password = signupPassword.getText();

            User user = new User(firstName, lastName, username, password);

            databaseHandler.addUser(user);

            nonNull = true;

        } else {
            shaker.shake();
            error.setContentText("Missing credentials...");
            error.show();
        }

        return nonNull;
    }
}
