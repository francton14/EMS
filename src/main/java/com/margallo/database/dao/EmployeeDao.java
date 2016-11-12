package com.margallo.database.dao;

import com.margallo.models.Employee;
import com.margallo.database.query_filters.EmployeeFilter;

import java.util.List;

/**
 * Created by franc on 11/11/2016.
 */
public interface EmployeeDao extends GenericDao<Employee> {

    public List<Employee> all(EmployeeFilter filter) throws Exception;

    public boolean exists(long employeeId) throws Exception;

}
