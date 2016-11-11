package com.margallo.services.impl;

import com.margallo.database.models.Employee;
import com.margallo.database.query_filters.EmployeeFilter;
import com.margallo.services.EmployeeService;

import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {



    public List<Employee> all() {
        return all(null);
    }

    public List<Employee> all(EmployeeFilter filter) {
        return null;
    }

    public String insert(Employee employee) {
        return null;
    }

    public Employee show(Long id) {
        return null;
    }

    public String update(Employee employee) {
        return null;
    }

    public void delete(Long id) {
    }

    public boolean exists(Long id) {
        return false;
    }

}
