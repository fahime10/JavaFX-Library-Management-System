package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignupButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane window;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        Shaker shaker = new Shaker(window);
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");

        loginSignupButton.setOnAction(event -> {
            SceneSwitcher sceneSwitcher = new SceneSwitcher();
            sceneSwitcher.switchScene(loginSignupButton, "/com/example/librarymanagementsystem/signup.fxml");
        });

        loginButton.setOnAction(event -> {
            User user = new User();

            user.setUsername(username.getText().trim());
            user.setPassword(password.getText());

            ResultSet result = databaseHandler.getUser(user);

            try {
                if (result.next()) {
                    boolean passwordCorrect = user.checkPassword(user.getPassword(), result.getString(5));

                    if (passwordCorrect) {
                        SceneSwitcher sceneSwitcher = new SceneSwitcher();
                        sceneSwitcher.switchScene(loginButton, "/com/example/librarymanagementsystem/library_view.fxml");

                    } else {
                        shaker.shake();
                        error.setContentText("User not found");
                        error.show();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Something went wrong with the database...");
            } catch (NullPointerException e) {
                shaker.shake();
                error.setContentText("No credentials have been provided");
                error.show();
            }
        });
    }
}