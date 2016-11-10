package com.margallo.services.impl;

import com.margallo.database.models.Employee;
import com.margallo.database.models.QEmployee;
import com.margallo.services.EmployeeFilter;
import com.margallo.services.EmployeeService;
import com.margallo.util.HibernateUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.hibernate.HibernateQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {

    public List<Employee> all() {
        return all(null);
    }

    public List<Employee> all(EmployeeFilter filter) {
        Session session = openSession();
        try {
            QEmployee employee = QEmployee.employee;
            HibernateQuery<Employee> query = new HibernateQuery<Employee>(session);
            BooleanBuilder builder = new BooleanBuilder();
            if (filter != null) {
                if (filter.getEmployeeId() != null) {
                    builder.and(employee.employeeId.eq(filter.getEmployeeId()));
                }
                if (StringUtils.isNotEmpty(filter.getFirstName())) {
                    builder.and(employee.firstName.eq(filter.getFirstName()));
                }
                if (StringUtils.isNotEmpty(filter.getLastName())) {
                    builder.and(employee.lastName.eq(filter.getLastName()));
                }
                if (StringUtils.isNotEmpty(filter.getPosition())) {
                    builder.and(employee.position.eq(filter.getPosition()));
                }
            }
            return query.from(employee).where(builder).fetch();
        } finally {
            session.close();
        }
    }

    public String insert(Employee employee) {
        Session session = openSession();
        String message = "Employee ID already exists";
        if (!exists(employee.getEmployeeId())) {
            session.merge(employee);
            session.flush();
            message = "Employee " + employee.getFirstName() + " " + employee.getLastName() + " successfully added.";
        }
        session.close();
        return message;
    }

    public Employee show(Long id) {
        Session session = openSession();
        try {
            QEmployee employee = QEmployee.employee;
            HibernateQuery<Employee> query = new HibernateQuery<Employee>(session);
            return query.from(employee).where(employee.id.eq(id)).fetchOne();
        } finally {
            session.close();
        }
    }

    public String update(Employee employee) {
        Session session = openSession();
        String message = "The employee ID given for modification already exists.";
        if (!exists(employee.getEmployeeId())) {
            session.merge(employee);
            session.flush();
            message = "Employee with ID successfully updated";
        }
        session.close();
        return message;
    }

    public void delete(Long id) {
        Session session = openSession();
        Employee employee = show(id);
        session.delete(employee);
        session.close();
    }

    public boolean exists(Long id) {
        Session session = openSession();
        try {
            QEmployee employee = QEmployee.employee;
            HibernateQuery<Employee> query = new HibernateQuery<Employee>(session);
            return query.from(employee).where(employee.employeeId.eq(id)).select(employee.count()).fetchOne() > 0;
        } finally {
            session.close();
        }
    }

    private Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

}
