package com.margallo.controllers;

import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.dao.impl.EmployeeDaoImpl;
import com.margallo.models.Employee;
import com.margallo.util.DialogGenerator;
import com.margallo.util.SceneSwitcher;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by franc on 11/7/2016.
 */
public class Home {

    private EmployeeDao employeeDao;

    private Properties properties;

    private ObservableList<Employee> employeeList;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnView;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Employee> employeeTable;

    public Home() {
        employeeDao = new EmployeeDaoImpl();
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/messages.properties"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog("An internal problem occurred", e.getMessage(), e).showAndWait();
        }
        updateEmployeeList();
    }

    @FXML
    private void onBtnNewClick() {
        try {
            SceneSwitcher.switchScene(btnNew.getScene(), getClass().getResource("../fxml/new_employee.fxml"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnSearchClick() {

    }

    @FXML
    private void onBtnViewClick() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = SceneSwitcher.switchSceneWithReturn(btnView.getScene(), getClass().getResource("../fxml/view_employee.fxml"));
            ViewEmployee controller = loader.getController();
            controller.setEmployee(selectedEmployee);
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnDeleteClick() {
        Optional<ButtonType> result = DialogGenerator.showConfirmationDialog(properties.getProperty("employee.delete.confirmation.header"),
                properties.getProperty("employee.delete.confirmation.message"))
                .showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                long id = employeeTable.getSelectionModel().getSelectedItem().getId();
                employeeDao.delete(id);
                employeeList.removeIf(employee -> employee.getId() == id);
            } catch (Exception e) {
                DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
            }
        }
    }

    @FXML
    private void initialize() {
        btnDelete.visibleProperty().bind(viewAndDeleteBtnBindings());
        btnView.visibleProperty().bind(viewAndDeleteBtnBindings());
        employeeTable.setItems(employeeList);
    }

    private BooleanBinding viewAndDeleteBtnBindings() {
        return Bindings.isNotEmpty(employeeTable.getSelectionModel().getSelectedItems());
    }

    private void updateEmployeeList() {
        try {
            List<Employee> employees = employeeDao.all();
            employeeList = FXCollections.observableList(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
