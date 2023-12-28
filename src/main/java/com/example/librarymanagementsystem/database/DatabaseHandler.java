package com.example.librarymanagementsystem.database;

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


}
