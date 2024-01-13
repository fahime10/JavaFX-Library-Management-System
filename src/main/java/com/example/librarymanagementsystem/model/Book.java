package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.animations.Shaker;
import com.example.librarymanagementsystem.database.DatabaseHandler;
import javafx.scene.control.Alert;

public class Book {
    private int id;
    private String title;
    private String author;
    private String desc;
    private int pages;

    private DatabaseHandler databaseHandler;

    public Book() {}

    public Book(int id, String title, String author, String desc, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.pages = pages;
    }

    public Book(String title, String author, String desc, int pages) {
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean createBook(String bookTitle, String bookAuthor, String bookDescription, String bookPages, Shaker shaker) {
        databaseHandler = new DatabaseHandler();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        boolean nonNull = false;

        if (bookTitle != "" && bookAuthor != "" && bookDescription != "" && Integer.parseInt(bookPages) != 0) {
            Book book = new Book(bookTitle, bookAuthor, bookDescription, Integer.parseInt(bookPages));

            databaseHandler.addBook(book);

            nonNull = true;

        } else {
            shaker.shake();
            error.setContentText("Please fill in all fields");
            error.show();
        }

        return nonNull;
    }

    @Override
    public String toString() {
        return "Title: \t" + this.getTitle() + "\n" +
                "Author:\t" + this.getAuthor() + "\n" +
                "Pages:\t" + this.getPages();
    }
}
