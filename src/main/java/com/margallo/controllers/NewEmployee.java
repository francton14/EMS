package com.margallo.controllers;

import com.margallo.database.models.Employee;
import com.margallo.services.EmployeeService;
import com.margallo.services.impl.EmployeeServiceImpl;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by franc on 11/7/2016.
 */

public class NewEmployee {

    private EmployeeService employeeService;

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
        employeeService = new EmployeeServiceImpl();
    }

    public void show(Window window) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../fxml/new_employee.fxml"));
        parent.getStylesheets().add(getClass().getResource("../css/styles.css").toExternalForm());
        Scene scene = new Scene(parent, 400, 400);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(window);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void onBtnSubmitClick() {
        Employee employee = new Employee();
        employee.setEmployeeId(Long.valueOf(txtEmployeeId.getText()));
        employee.setFirstName(txtFirstName.getText());
        employee.setLastName(txtLastName.getText());
        employee.setPosition(txtPosition.getText());
        employeeService.insert(employee);
    }

    @FXML
    private void onBtnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        btnSubmit.disableProperty().unbind();
        stage.close();
    }

    @FXML
    public void initialize() {
        btnSubmit.disableProperty().bind(Bindings.isEmpty(txtEmployeeId.textProperty())
                .or(Bindings.isEmpty(txtFirstName.textProperty()))
                .or(Bindings.isEmpty(txtLastName.textProperty()))
                .or(Bindings.isEmpty(txtPosition.textProperty())));
    }

}
