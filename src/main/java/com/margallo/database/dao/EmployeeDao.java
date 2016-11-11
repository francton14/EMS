package com.margallo.database.dao;

import com.margallo.database.models.Employee;
import com.margallo.database.query_filters.EmployeeFilter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by franc on 11/11/2016.
 */
public interface EmployeeDao extends GenericDao<Employee> {

    public List<Employee> show(EmployeeFilter filter) throws SQLException;

}
