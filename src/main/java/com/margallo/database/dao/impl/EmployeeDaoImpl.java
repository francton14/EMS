package com.margallo.database.dao.impl;

import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.custom.CustomSyntax;
import com.margallo.database.DatabaseConnection;
import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.models.Employee;
import com.margallo.database.query_filters.EmployeeFilter;
import com.margallo.database.tables.EmployeeTable;
import com.margallo.database.util.RsToObject;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 11/11/2016.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Override
    public List<Employee> all() throws SQLException {
        return show(null);
    }

    @Override
    public void insert(Employee model) throws SQLException {
        initialize();
        String query = (new InsertQuery(EmployeeTable.getTable()))
                .addColumn(EmployeeTable.getEmployeeId(), model.getEmployeeId())
                .addColumn(EmployeeTable.getFirstName(), model.getFirstName())
                .addColumn(EmployeeTable.getLastName(), model.getLastName())
                .addColumn(EmployeeTable.getPosition(), model.getPosition())
                .validate().toString();
        try {
            statement.execute(query);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public Employee show(long id) throws SQLException {
        initialize();
        Employee employee = null;
        String query = (new SelectQuery())
                .addAllTableColumns(EmployeeTable.getTable())
                .addCondition(BinaryCondition.equalTo(EmployeeTable.getId(), id))
                .validate().toString();
        try {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                employee = RsToObject.convertToEmployee(resultSet);
            }
            return employee;
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public void update(Employee model) throws SQLException {
        initialize();
        String query = (new UpdateQuery(EmployeeTable.getTable()))
                .addSetClause(EmployeeTable.getEmployeeId(), model.getEmployeeId())
                .addSetClause(EmployeeTable.getFirstName(), model.getFirstName())
                .addSetClause(EmployeeTable.getPosition(), model.getPosition())
                .addCondition(BinaryCondition.equalTo(EmployeeTable.getId(), model.getId()))
                .validate().toString();
        try {
            statement.execute(query);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        initialize();
        String query = (new DeleteQuery(EmployeeTable.getTable()))
                .addCondition(BinaryCondition.equalTo(EmployeeTable.getId(), id))
                .validate().toString();
        try {
            statement.execute(query);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public boolean exists(long id) throws SQLException {
        initialize();
        String query = (new SelectQuery())
                .addCustomColumns(FunctionCall.countAll())
                .addCondition(BinaryCondition.equalTo(EmployeeTable.getId(), id))
                .validate().toString();
        try {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public List<Employee> show(EmployeeFilter filter) throws SQLException {
        initialize();
        List<Employee> employees = new ArrayList<>();
        SelectQuery selectQuery = new SelectQuery().addAllTableColumns(EmployeeTable.getTable());
        if (filter != null) {
            if (filter.getEmployeeId() != null) {
                selectQuery.addCondition(BinaryCondition.equalTo(EmployeeTable.getEmployeeId(), filter.getEmployeeId()));
            }
            if (StringUtils.isNotEmpty(filter.getFirstName())) {
                selectQuery.addCondition(BinaryCondition.equalTo(EmployeeTable.getFirstName(), filter.getFirstName()));
            }
            if (StringUtils.isNotEmpty(filter.getLastName())) {
                selectQuery.addCondition(BinaryCondition.equalTo(EmployeeTable.getLastName(), filter.getLastName()));
            }
            if (StringUtils.isNotEmpty(filter.getPosition())) {
                selectQuery.addCondition(BinaryCondition.equalTo(EmployeeTable.getPosition(), filter.getPosition()));
            }
        }
        String query = selectQuery.validate().toString();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                employees.add(RsToObject.convertToEmployee(resultSet));
            }
            return employees;
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    private void initialize() throws SQLException {
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
    }

}
