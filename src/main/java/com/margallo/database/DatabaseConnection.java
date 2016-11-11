package com.margallo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by franc on 11/10/2016.
 */
public class DatabaseConnection {

    private static Connection connection;

    private static Statement statement;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ems", properties.getProperty("database.username"), properties.getProperty("database.password"));
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

}
