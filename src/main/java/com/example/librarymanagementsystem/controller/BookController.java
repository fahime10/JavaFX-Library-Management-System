package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Library;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    private ListView<Book> bookListview;

    @FXML
    private AnchorPane window;

    @FXML
    void initialize() {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Shaker shaker = new Shaker(window);

        if (bookCreate != null) {
            bookCreate.setOnAction(event -> {
                Book newBook = new Book();
                boolean successful = newBook.createBook(bookTitle.getText(), bookAuthor.getText(),
                        bookDescription.getText(), bookPages.getText(), shaker);

                if (successful) {
                    sceneSwitcher.switchScene(bookCreate, "/com/example/librarymanagementsystem/library_view.fxml");
                }
            });

            backButton.setOnAction(event ->
                    sceneSwitcher.switchScene(backButton, "/com/example/librarymanagementsystem/library_view.fxml")
            );

        } else if (addBookButton != null) {
            Library library = new Library();
            Book currentBook;

            ResultSet storedBooks = library.getAllBooks();

            try {
                while (storedBooks.next()) {
                    Book book = new Book(storedBooks.getInt(1), storedBooks.getString(2),
                            storedBooks.getString(3), storedBooks.getString(4), storedBooks.getInt(5));

                    library.addBook(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                shaker.shake();
            }

            bookListview.getItems().addAll(library.getBooks());

            addBookButton.setOnAction(event ->
                    sceneSwitcher.switchScene(addBookButton, "/com/example/librarymanagementsystem/create_book.fxml")
            );

            editBookButton.setOnAction(event -> {

            });

            deleteBookButton.setOnAction(event -> {

            });
        }
    }
}
