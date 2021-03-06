package com.margallo.database;


import com.google.common.reflect.ClassPath;
import com.healthmarketscience.common.util.AppendableExt;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.custom.CustomSyntax;
import com.healthmarketscience.sqlbuilder.custom.mysql.MysObjects;
import com.healthmarketscience.sqlbuilder.dbspec.Constraint;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import org.apache.commons.dbutils.DbUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by franc on 11/10/2016.
 */
public class TableDefinition {

    public void createTables() throws IOException {
        Reflections reflections = new Reflections("com.margallo.database.tables", new SubTypesScanner(false));
        reflections.getSubTypesOf(Object.class).forEach(classInfo -> {
            Connection connection = null;
            Statement statement = null;
            try {
                connection = DatabaseConnection.getConnection();
                statement = connection.createStatement();
                DbTable table = (DbTable) classInfo.getMethod("getTable").invoke(classInfo);
                statement.execute(generateQuery(table));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(connection, statement, null);
            }
        });
    }

    private String generateQuery(DbTable table) {
        return new CreateTableQuery(table, true)
                .addCustomization(MysObjects.IF_NOT_EXISTS_TABLE)
                .validate().toString();
    }

}
