package com.ucab.dollerappfx;

import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.IOException;
import ManejadorBD.ManejadorBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static Color naranja = Color.web("#FF7B52");
    static Color aguamarina = Color.web("#68C3B9");

    //--------------------------Propiedades Usuario-------------------------------//
    public static ATM usuario;
    public static Administrador admin;
    public static ManejadorBD manejadorBD;
    
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        // Hay que cargar o crear las listas (Aqui estoy usando unos de prueba)
        manejadorBD = new ManejadorBD();
        scene = new Scene(loadFXML("LogIn"), 1280, 800);
        stage.setTitle("Dollder App FX");
        try {
            stage.getIcons().add(new Image("Assets/LogoDollder.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
				event.consume();
				logout(stage);	
			});
    }
    
//    public static void setUsuario(String nombre, String clave, String territorio, String email, String ){
//        this.usuario = new ATM(nombre, );
//    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public void logout(Stage stage){	
        try {
            manejadorBD.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.err.println("Cerrado");
            stage.close();
        }
    }



    public static void main(String[] args) {
        launch();
    }

}