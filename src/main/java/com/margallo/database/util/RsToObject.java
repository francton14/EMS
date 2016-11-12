package com.margallo.database.util;

import com.margallo.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by franc on 11/11/2016.
 */
public class RsToObject {

    public static Employee convertToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setEmployeeId(resultSet.getLong("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setPosition(resultSet.getString("position"));
        return employee;
    }

}
