package com.margallo.controllers;

import com.margallo.database.models.Employee;
import com.margallo.services.EmployeeService;
import com.margallo.services.impl.EmployeeServiceImpl;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

/**
 * Created by franc on 11/7/2016.
 */
public class Home {

    private EmployeeService employeeService;

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

    private ObservableList<Employee> employeeList;

    public Home() {
        employeeService = new EmployeeServiceImpl();
    }

    public void show(Window window) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        parent.getStylesheets().add(getClass().getResource("../css/styles.css").toExternalForm());
        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initOwner(window);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onBtnNewClick() {
        NewEmployee newEmployee = new NewEmployee();
        try {
            newEmployee.show(btnNew.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateEmployeeList();
    }

    @FXML
    private void onBtnSearchClick() {

    }

    @FXML
    private void onBtnViewClick() {

    }

    @FXML
    private void onBtnDeleteClick() {

    }

    @FXML
    private void initialize() {
        btnDelete.visibleProperty().bind(viewAndDeleteBtnBindings());
        btnView.visibleProperty().bind(viewAndDeleteBtnBindings());
        updateEmployeeList();
        employeeTable.setItems(employeeList);
    }

    private BooleanBinding viewAndDeleteBtnBindings() {
        return Bindings.isNotEmpty(employeeTable.getSelectionModel().getSelectedItems());
    }

    private void updateEmployeeList() {
        List<Employee> employees = employeeService.all();
    }

}
