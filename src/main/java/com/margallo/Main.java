package com.margallo;

import com.margallo.database.TableDefinition;
import com.margallo.util.DialogGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Properties;

/**
 * Created by franc on 11/10/2016.
 */
public class Main extends Application {

    private Properties properties;

    public Main() {
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/messages.properties"));
            new TableDefinition().createTables();
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog("An internal problem occurred", e.getMessage(), e).showAndWait();
        }
    }

    public void start(Stage primaryStage) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
            parent.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
            Scene scene = new Scene(parent, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog(properties.getProperty("internal.error.header"), e.getMessage(), e).showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
