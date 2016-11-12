package com.margallo.controllers;

import com.margallo.database.dao.EmployeeDao;
import com.margallo.database.dao.impl.EmployeeDaoImpl;
import com.margallo.models.Employee;
import com.margallo.util.DialogGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private void initialize() {
        txtEmployeeId.disableProperty().bind(showAdvanced);
        txtEmployeeId.managedProperty().bind(showAdvanced);
        txtFirstName.disableProperty().bind(showAdvanced);
        txtFirstName.managedProperty().bind(showAdvanced);
        txtLastName.disableProperty().bind(showAdvanced);
        txtLastName.managedProperty().bind(showAdvanced);
        txtPosition.disableProperty().bind(showAdvanced);
        txtPosition.managedProperty().bind(showAdvanced);
    }

}
