package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BookController {
    @FXML
    private ImageView addBookButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField bookAuthor;

    @FXML
    private Button bookCreate;

    @FXML
    private TextArea bookDescription;

    @FXML
    private PasswordField bookPages;

    @FXML
    private TextField bookTitle;

    @FXML
    private AnchorPane window;

    @FXML
    void initialize() {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();

        if (addBookButton != null) {
            addBookButton.setOnMouseClicked(event -> {
                sceneSwitcher.switchScene(addBookButton, "/com/example/librarymanagementsystem/create_book.fxml");
            });
        } else {
            bookCreate.setOnAction(event -> {
                boolean successful = createBook();

                if (successful) {
                    sceneSwitcher.switchScene(bookCreate, "/com/example/librarymanagementsystem/login.fxml");
                }
            });

            backButton.setOnAction(event -> {
                sceneSwitcher.switchScene(backButton, "/com/example/librarymanagementsystem/login.fxml");
            });
        }
    }

    private boolean createBook() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Shaker shaker = new Shaker(window);
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        boolean nonNull = false;

        if (bookTitle.getText() != "" && bookAuthor.getText() != "" &&
            bookDescription.getText() != "" && Integer.parseInt(bookPages.getText()) != 0) {


            String title = bookTitle.getText();
            String author = bookAuthor.getText();
            String desc = bookDescription.getText();
            int pages = Integer.parseInt(bookPages.getText());

            Book book = new Book(title, author, desc, pages);

            databaseHandler.addBook(book);

            nonNull = true;

        } else {
            shaker.shake();
            error.setContentText("Please fill in all fields");
            error.show();
        }

        return nonNull;

    }
}
