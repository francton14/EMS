package com.margallo.database;

import com.healthmarketscience.sqlbuilder.AlterTableQuery;
import com.healthmarketscience.sqlbuilder.ConstraintClause;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

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
        DbSchema dbSchema = dbSpec.getDefaultSchema();
        table = dbSchema.addTable("employee");
        id = table.addColumn("id", "bigint", null);
        employeeId = table.addColumn("employee_id", "bigint", null);
        firstName = table.addColumn("first_name", "varchar", 255);
        lastName = table.addColumn("last_name", "varchar", 255);
        position = table.addColumn("position", "varchar", 255);
        ConstraintClause.primaryKey(id);
    }

}
