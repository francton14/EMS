package com.margallo.database.tables;

import com.healthmarketscience.sqlbuilder.ConstraintClause;
import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.dbspec.Constraint;
import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

/**
 * Created by franc on 11/10/2016.
 */
public class EmployeeTable {

    private static DbTable table;
    private static DbColumn id;
    private static DbColumn employeeId;
    private static DbColumn firstName;
    private static DbColumn lastName;
    private static DbColumn position;

    static {
        DbSpec dbSpec = new DbSpec();
        DbSchema dbSchema = dbSpec.addDefaultSchema();
        table = dbSchema.addTable("employee");
        id = table.addColumn("id", "bigint", null);
        employeeId = table.addColumn("employee_id", "bigint", null);
        firstName = table.addColumn("first_name", "varchar", 255);
        lastName = table.addColumn("last_name", "varchar", 255);
        position = table.addColumn("position", "varchar", 255);
    }

    public static DbTable getTable() {
        return table;
    }

    public static DbColumn getId() {
        return id;
    }

    public static DbColumn getEmployeeId() {
        return employeeId;
    }

    public static DbColumn getFirstName() {
        return firstName;
    }

    public static DbColumn getLastName() {
        return lastName;
    }

    public static DbColumn getPosition() {
        return position;
    }

}
