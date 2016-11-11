package com.margallo.database;


import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by franc on 11/10/2016.
 */
public class TableDefinition {

    private static Statement statement;

    static {
        statement = DatabaseConnection.getStatement();
    }

    public static void createTable(String query) throws SQLException {
        statement.execute(query);
    }

}
