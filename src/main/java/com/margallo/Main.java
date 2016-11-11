package com.margallo;

import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.custom.mysql.MysObjects;
import com.healthmarketscience.sqlbuilder.dbspec.Constraint;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbConstraint;
import com.margallo.controllers.Home;
import com.margallo.database.TableDefinition;
import com.margallo.database.tables.EmployeeTable;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by franc on 11/10/2016.
 */
public class Main extends Application {

    private Home home;

    public Main() {
        home = new Home();
    }

    public void start(Stage primaryStage) throws Exception {
        home.show(primaryStage);
    }

    public static void main(String[] args) {
        try {
            TableDefinition.createTable(new CreateTableQuery(EmployeeTable.getTable(), true).addConstraints(new DbConstraint(EmployeeTable.getEmployeeId(), "employee_pk", Constraint.Type.PRIMARY_KEY)).addCustomization(MysObjects.IF_NOT_EXISTS_TABLE).validate().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }

}
