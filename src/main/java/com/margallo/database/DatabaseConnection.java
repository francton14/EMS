package com.margallo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by franc on 11/10/2016.
 */
public class DatabaseConnection {

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ems",
                properties.getProperty("database.username"),
                properties.getProperty("database.password"));
    }

}
