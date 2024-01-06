package com.example.librarymanagementsystem.database;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.User;

import java.sql.*;

public class DatabaseHandler extends Configuration {
    Connection dbConnection;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void addUser(User user) {
        String query = "INSERT INTO " + Values.USER_TABLE + "(" + Values.FIRST_NAME +
                       ", " + Values.LAST_NAME + ", " + Values.USERNAME + ", " +
                       Values.PASSWORD + ") VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet result = null;

        if ((!user.getUsername().equals("") && !user.getPassword().equals(""))) {
            String query = "SELECT * FROM " + Values.USER_TABLE + " WHERE " +
                            Values.USERNAME + "=? ";

            try {
                PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());

                result = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ResultSet getAllBooks() {
        ResultSet result = null;

        String query = "SELECT * FROM " + Values.BOOK_TABLE;

        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);

            result = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void addBook(Book book) {
        String query = "INSERT INTO " + Values.BOOK_TABLE + "(" + Values.BOOK_TITLE + ", " +
                       Values.BOOK_AUTHOR + ", " + Values.BOOK_AUTHOR + ", " + Values.BOOK_PAGES + ") " +
                       "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDesc());
            preparedStatement.setInt(4, book.getPages());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
