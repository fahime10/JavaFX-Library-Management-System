package com.example.librarymanagementsystem.model;

public class Book {
    private String title;
    private String author;
    private String desc;
    private int pages;

    public Book() {}

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
}
