package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Library;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {
    @FXML
    private Button backButton;

    @FXML
    private TextField bookAuthor;

    @FXML
    private Button bookCreate;

    @FXML
    private TextArea bookDescription;

    @FXML
    private TextField bookPages;

    @FXML
    private TextField bookTitle;

    @FXML
    private Button addBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private Button editBookButton;

    @FXML
    private ListView<String> bookListvew;

    @FXML
    private AnchorPane window;

    @FXML
    void initialize() {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Shaker shaker = new Shaker(window);

        if (bookCreate != null) {
            bookCreate.setOnAction(event -> {
                boolean successful = createBook(databaseHandler, shaker);

                if (successful) {
                    sceneSwitcher.switchScene(bookCreate, "/com/example/librarymanagementsystem/library_view.fxml");
                }
            });

            backButton.setOnAction(event ->
                    sceneSwitcher.switchScene(backButton, "/com/example/librarymanagementsystem/login.fxml")
            );

        } else if (addBookButton != null) {
            Library books = new Library();
            Book currentBook;

            ResultSet storedBooks = databaseHandler.getAllBooks();

            try {
                while (storedBooks.next()) {
                    Book book = new Book(storedBooks.getString(2), storedBooks.getString(3),
                            storedBooks.getString(4), storedBooks.getInt(5));

                    books.addBook(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                shaker.shake();
            }

            for (int i = 0; i < books.getBooks().size(); i++) {
                bookListvew.getItems().add(books.toString(i));
            }

            addBookButton.setOnAction(event ->
                    sceneSwitcher.switchScene(addBookButton, "/com/example/librarymanagementsystem/create_book.fxml")
            );

            editBookButton.setOnAction(event -> {

            });

            deleteBookButton.setOnAction(event -> {

            });
        }
    }

    private boolean createBook(DatabaseHandler databaseHandler, Shaker shaker) {
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
