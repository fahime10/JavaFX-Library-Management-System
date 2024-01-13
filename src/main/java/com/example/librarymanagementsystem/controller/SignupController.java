package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        Shaker shaker = new Shaker(window);

        signupSubmitButton.setOnAction(event -> {
            User newUser = new User();
            boolean successful = newUser.createUser(signupFirstName.getText(), signupLastName.getText(),
                    signupUsername.getText(), signupPassword.getText(), shaker);

            if (successful) {
                sceneSwitcher.switchScene(signupSubmitButton, "/com/example/librarymanagementsystem/login.fxml");
            }
        });

        backButton.setOnAction(event -> {
            sceneSwitcher.switchScene(signupSubmitButton, "/com/example/librarymanagementsystem/login.fxml");
        });
    }
}
