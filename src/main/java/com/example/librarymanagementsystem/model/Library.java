package com.example.librarymanagementsystem.model;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> library;

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

    public String toString(int index) {
        return "Title: \t" + library.get(index).getTitle() + "\n" +
                "Author:\t" + library.get(index).getAuthor() + "\n" +
                "Pages:\t" + library.get(index).getPages();
    }
}
