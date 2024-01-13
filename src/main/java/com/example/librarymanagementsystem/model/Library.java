package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.database.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> library;
    private DatabaseHandler databaseHandler;

    public Library() {
        library = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return library;
    }

    public Book getBook(int index) {
        return library.get(index);
    }

    public void addBook(Book book) {
        library.add(book);
    }

    public void editBook() {

    }

    public void deleteBook(Book book) {
        library.remove(book);
    }

    public ResultSet getAllBooks() {
        databaseHandler = new DatabaseHandler();

        return databaseHandler.getAllBooks();
    }
}
