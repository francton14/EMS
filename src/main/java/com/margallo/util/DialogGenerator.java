package com.margallo.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by franc on 11/11/2016.
 */
public class DialogGenerator {

    public static Alert showDialog(String header, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.getDialogPane().getStylesheets().add(DialogGenerator.class.getResource("../css/styles.css").toExternalForm());
        alert.setTitle("EMS");
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert;
    }

    public static Alert showExceptionDialog(String header, String message, Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().getStylesheets().add(DialogGenerator.class.getResource("../css/styles.css").toExternalForm());
        alert.setTitle("EMS");
        alert.setHeaderText(header);
        alert.setContentText(message);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        return alert;
    }

    public static Alert showConfirmationDialog(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().getStylesheets().add(DialogGenerator.class.getResource("../css/styles.css").toExternalForm());
        alert.setTitle("EMS");
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert;
    }

}
