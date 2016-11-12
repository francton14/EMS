package com.margallo.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by franc on 11/12/2016.
 */
public class SceneSwitcher {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(SceneSwitcher.class.getResourceAsStream("/messages.properties"));
        } catch (Exception e) {
            DialogGenerator.showExceptionDialog("An internal problem occurred", e.getMessage(), e).showAndWait();
        }
    }

    public static void switchScene(Scene scene, URL location) throws Exception {
        Parent parent = FXMLLoader.load(location);
        parent.getStylesheets().add(SceneSwitcher.class.getResource("../css/styles.css").toExternalForm());
        scene.setRoot(parent);
    }

    public static FXMLLoader switchSceneWithReturn(Scene scene, URL location) throws Exception {
        FXMLLoader loader = new FXMLLoader(location);
        Parent parent = loader.load();
        parent.getStylesheets().add(SceneSwitcher.class.getResource("../css/styles.css").toExternalForm());
        scene.setRoot(parent);
        return loader;
    }

}
