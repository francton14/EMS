package com.margallo;

import com.margallo.controllers.Home;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by franc on 11/10/2016.
 */
public class Main extends Application {

    private Home home;

    public Main() {
        home = new Home();
    }

    public void start(Stage primaryStage) throws Exception {
        home.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
