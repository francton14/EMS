package com.margallo.services;

import com.margallo.database.models.Employee;
import com.margallo.database.query_filters.EmployeeFilter;

import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public interface EmployeeService {

    public List<Employee> all();

    public List<Employee> all(EmployeeFilter filter);

    public String insert(Employee employee);

    public Employee show(Long id);

    public String update(Employee employee);

    public void delete(Long id);

    public boolean exists(Long id);

}
