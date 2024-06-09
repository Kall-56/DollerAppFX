package com.ucab.dollerappfx;

import Classes.Evento;
import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static Color naranja = Color.web("#FF7B52");
    static Color aguamarina = Color.web("#68C3B9");

    public static ObservableList<Medico> listMedicos;

    public static ObservableList<Institucion> listInstituciones;

    public static ObservableList<Farmacia> listFarmacias;

    public static ObservableList<Evento> listEventos;
    
    @Override
    public void start(Stage stage) throws IOException {
        // Hay que cargar o crear las listas (Aqui estoy usando unos de prueba)
        ArrayList<Evento> l1 = new ArrayList<>();
        l1.add(new Evento("Visita Hospitalaria","Dubin Ludmila", "24/05/2024", 2, "Supongamos que"));
        l1.add(new Evento("Visita Hospitalaria", "Dubin Ludmila", "01/10/2024", 4, "Pasarán cosas"));

        ArrayList<Evento> l2 = new ArrayList<>();
        l2.add(new Evento("Actividad Promocional", "Alex", "12/04/24", 1, "Evento intergaláctico"));
        l2.add(new Evento("Actividad Promocional", "Alex", "17/09/24", 3, "Se aprecian cositas"));

        App.listMedicos = FXCollections.observableArrayList(
                new Medico("Baez Arnaldo", "Avenida Vargas Entre Las Palmas y Caerrera 32", "04143515161", 'V', 7884170, "No se", "Urólogo", "arnaldobaez@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes"),
                new Medico("Dubin Ludmila", "Calle 20 Con Carrera 23 Lara", "18325313408", 'V', 7329907, "No se", "Ginecólogo", "ludmiladubim@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes", l1)
        );
        App.listInstituciones = FXCollections.observableArrayList(
                new Institucion("Manu", "Por alla", "412", 'V', 31423309, "No se", "Alguien", "Ciencias"),
                new Institucion("Pepe", "Por aca", "426", 'V', 10544904, "No se", "Otro alguien", "Humanidades"),
                new Institucion("Pedro", "Un lugar", "412", 'V', 9742912, "No se", "Juanito", "Ingeniería")
        );
        App.listFarmacias = FXCollections.observableArrayList(
                new Farmacia("Alex", "Por aqui", "414", 'J', 11225210, "No se", "Un extraño", "b@gmail.com", 1, "Perpetua", "Acetaminofen", l2),
                new Farmacia("Monsalve", "SJT", "424", 'V', 32848109, "No se", "Pepito", "diablo@gmail.com", 0, "30 años", "Migren")

        );

        listEventos = FXCollections.observableArrayList();
        listEventos.addAll(l1);
        listEventos.addAll(l2);

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