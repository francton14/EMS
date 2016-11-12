package com.margallo.controllers;

import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.dao.impl.EmployeeDaoImpl;
import com.margallo.models.Employee;
import com.margallo.util.DialogGenerator;
import com.margallo.util.SceneSwitcher;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.glyphfont.Glyph;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by franc on 11/12/2016.
 */
public class Search {

    private EmployeeDao employeeDao;

    private Properties properties;

    private BooleanProperty showAdvanced;

    private ObservableList<Employee> employeeList;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPosition;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdvanced;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblPosition;

    @FXML
    private TableView employeeTable;

    public Search() {
        employeeDao = new EmployeeDaoImpl();
        properties = new Properties();
        showAdvanced = new SimpleBooleanProperty(false);
        try {
            properties.load(getClass().getResourceAsStream("/messages.properties"));
        } catch (IOException e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void onBtnSearchClick() {

    }

    @FXML
    private void onBtnAdvancedClick() {
        showAdvanced.setValue(!showAdvanced.getValue());
        if (showAdvanced.getValue()) {
            btnAdvanced.setGraphic(new Glyph("FontAwesome", "CHEVRON_UP"));
            btnAdvanced.setText("Hide Advanced");
        } else {
            btnAdvanced.setGraphic(new Glyph("FontAwesome", "CHEVRON_DOWN"));
            btnAdvanced.setText("Show Advanced");
        }
    }

    @FXML
    private void onBtnBackClick() {
        try {
            SceneSwitcher.switchScene(btnBack.getScene(), getClass().getResource("../fxml/home.fxml"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    @FXML
    private void initialize() {
        txtFirstName.visibleProperty().bind(showAdvanced);
        txtFirstName.managedProperty().bind(showAdvanced);
        lblFirstName.visibleProperty().bind(showAdvanced);
        lblFirstName.managedProperty().bind(showAdvanced);
        txtLastName.visibleProperty().bind(showAdvanced);
        txtLastName.managedProperty().bind(showAdvanced);
        lblLastName.visibleProperty().bind(showAdvanced);
        lblLastName.managedProperty().bind(showAdvanced);
        txtPosition.visibleProperty().bind(showAdvanced);
        txtPosition.managedProperty().bind(showAdvanced);
        lblPosition.visibleProperty().bind(showAdvanced);
        lblPosition.managedProperty().bind(showAdvanced);
    }

}
