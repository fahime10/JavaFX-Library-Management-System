package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private DatabaseHandler databaseHandler;

    public User() {}

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;

        this.password = hashPassword(password);
    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean createUser(String firstName, String lastName, String username, String password, Shaker shaker) {
        databaseHandler = new DatabaseHandler();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        boolean nonNull = false;

        if (firstName != "" && lastName != ""  && username != "" && password != "") {
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
