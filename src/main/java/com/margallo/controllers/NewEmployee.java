package com.margallo.controllers;

import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.dao.impl.EmployeeDaoImpl;
import com.margallo.models.Employee;
import com.margallo.util.DialogGenerator;
import com.margallo.util.SceneSwitcher;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by franc on 11/7/2016.
 */

public class NewEmployee {

    private EmployeeDao employeeDao;

    private Properties properties;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPosition;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    public NewEmployee() {
        employeeDao = new EmployeeDaoImpl();
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/messages.properties"));
        } catch (IOException e) {
            DialogGenerator.showExceptionDialog("An internal problem occurred", e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnSubmitClick() {
        Employee employee = new Employee();
        employee.setEmployeeId(Long.valueOf(txtEmployeeId.getText()));
        employee.setFirstName(txtFirstName.getText());
        employee.setLastName(txtLastName.getText());
        employee.setPosition(txtPosition.getText());
        try {
            if (!employeeDao.exists(employee.getEmployeeId())) {
                employeeDao.insert(employee);
                DialogGenerator.showDialog(properties.getProperty("employee.insert.success.header"),
                        properties.getProperty("employee.insert.success.message"),
                        Alert.AlertType.INFORMATION).showAndWait();
                SceneSwitcher.switchScene(btnSubmit.getScene(), getClass().getResource("../fxml/home.fxml"));
            } else {
                DialogGenerator.showDialog(properties.getProperty("employee.insert.exist.header"),
                        properties.getProperty("employee.insert.exist.message"),
                        Alert.AlertType.ERROR).showAndWait();
            }
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnCancelClick() {
        try {
            btnSubmit.disableProperty().unbind();
            SceneSwitcher.switchScene(btnSubmit.getScene(), getClass().getResource("../fxml/home.fxml"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    public void initialize() {
        btnSubmit.disableProperty().bind(Bindings.isEmpty(txtEmployeeId.textProperty())
                .or(Bindings.isEmpty(txtFirstName.textProperty()))
                .or(Bindings.isEmpty(txtLastName.textProperty()))
                .or(Bindings.isEmpty(txtPosition.textProperty())));
    }

}
