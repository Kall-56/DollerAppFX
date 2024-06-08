package com.ucab.dollerappfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static Color naranja = Color.web("#FF7B52");
    static Color aguamarina = Color.web("#68C3B9");
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LogIn"), 1280, 800);
        stage.setTitle("Dollder App");
        stage.getIcons().add(new Image("/Assets/logo_Dollder.png"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}