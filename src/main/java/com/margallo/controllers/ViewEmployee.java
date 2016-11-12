package com.margallo.controllers;

import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.dao.impl.EmployeeDaoImpl;
import com.margallo.models.Employee;
import com.margallo.util.DialogGenerator;
import com.margallo.util.SceneSwitcher;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Optional;
import java.util.Properties;

/**
 * Created by franc on 11/12/2016.
 */
public class ViewEmployee {

    private EmployeeDao employeeDao;

    private Properties properties;

    private Employee employee;

    private BooleanProperty showUpdatePane;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPosition;

    @FXML
    private HBox paneViewControls;

    @FXML
    private HBox paneUpdate;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    public ViewEmployee() {
        employeeDao = new EmployeeDaoImpl();
        properties = new Properties();
        showUpdatePane = new SimpleBooleanProperty(true);
        try {
            properties.load(getClass().getResourceAsStream("/messages.properties"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnSubmitClick() {
        long employeeId = Long.valueOf(txtEmployeeId.getText());
        try {
            if (employeeId != employee.getEmployeeId() && employeeDao.exists(employeeId)) {
                DialogGenerator.showDialog(properties.getProperty("employee.update.exist.header"),
                        properties.getProperty("employee.update.exist.message"),
                        Alert.AlertType.ERROR).showAndWait();
            } else {
                employee.setEmployeeId(employeeId);
                employee.setFirstName(txtFirstName.getText());
                employee.setLastName(txtLastName.getText());
                employee.setPosition(txtPosition.getText());
                employeeDao.update(employee);
                DialogGenerator.showDialog(properties.getProperty("employee.update.success.header"),
                        properties.getProperty("employee.update.success.message"),
                        Alert.AlertType.INFORMATION).showAndWait();
                setEmployee(employee);
                showUpdatePane.setValue(true);
            }
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

     @FXML
     private void onBtnCancelClick() {
         showUpdatePane.setValue(true);
     }

    @FXML
    private void onBtnUpdateClick() {
        showUpdatePane.setValue(false);
    }

    @FXML
    private void onBtnDeleteClick() {
        Optional<ButtonType> result = DialogGenerator.showConfirmationDialog(properties.getProperty("employee.delete.confirmation.header"),
                properties.getProperty("employee.delete.confirmation.message"))
                .showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                employeeDao.delete(employee.getId());
                SceneSwitcher.switchScene(btnSubmit.getScene(), getClass().getResource("../fxml/home.fxml"));
            } catch (Exception e) {
                DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
            }
        }
    }

    @FXML
    private void onBtnBackClick() {
        try {
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
        txtEmployeeId.disableProperty().bind(showUpdatePane);
        txtFirstName.disableProperty().bind(showUpdatePane);
        txtLastName.disableProperty().bind(showUpdatePane);
        txtPosition.disableProperty().bind(showUpdatePane);
        paneUpdate.visibleProperty().bind(showUpdatePane.not());
        paneUpdate.managedProperty().bind(showUpdatePane.not());
        paneViewControls.visibleProperty().bind(showUpdatePane);
        paneViewControls.managedProperty().bind(showUpdatePane);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        txtEmployeeId.setText(employee.getEmployeeId().toString());
        txtFirstName.setText(employee.getFirstName());
        txtLastName.setText(employee.getLastName());
        txtPosition.setText(employee.getPosition());
    }

}
