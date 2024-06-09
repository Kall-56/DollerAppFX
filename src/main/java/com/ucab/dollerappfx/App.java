package com.ucab.dollerappfx;

import Classes.*;
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

    //--------------------------Propiedades Usuario-------------------------------//
    public static ArrayList<ATM> listaAtm;
    public static ATM usuario;
    public static Administrador admin;
    
    @Override
    public void start(Stage stage) throws IOException {
        // Hay que cargar o crear las listas (Aqui estoy usando unos de prueba)
        ArrayList<Evento> l1 = new ArrayList<>();
        l1.add(new Evento("Promocion de shampus","Hospital Rotario de Barquisimeto Fundarosa", "12/03/2023", 2, "Se hizo entrega en el hospital a personal y a pacientes en espera muestras del producto shampu Nopucid"));

        ArrayList<Evento> l3 = new ArrayList<>();
        l3.add(new Evento("Visita Hospitalaria", "Dubin Ludmila", "01/10/2024", 4, ""));

        ArrayList<Evento> l2 = new ArrayList<>();
        l2.add(new Evento("Actividad Promocional", "Farmacia Alegria", "12/04/2024", 1, "Se entregaron productos promocionales"));
        l2.add(new Evento("Actividad Promocional", "Farmacia Alegria", "27/09/2024", 3, "Se realizo una actividad grupal para los empleados"));

        ObservableList<Medico> listMedicos = FXCollections.observableArrayList(
                new Medico("Baez Arnaldo", "Avenida Vargas Entre Las Palmas y Caerrera 32", "04143515161", 'V', 7884170, "", "Urólogo", "arnaldobaez@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes"),
                new Medico("Dubin Ludmila", "Calle 20 Con Carrera 23 Lara", "18325313408", 'V', 7329907, "", "Ginecólogo", "ludmiladubim@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes", l3),
                new Medico("Herrera Luisana", "Calle 41 Entre Carrera 20 y 21", "04245004027", 'V', 18785005, "", "Medicina Interna", "luisanaherreradr@hotmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes")
        );
        ObservableList<Institucion> listInstituciones = FXCollections.observableArrayList(
                new Institucion("Hospital Central Universitario 'Antonio Maria Pineda'", "Av. Libertador, Barquisimeto 3001, Lara", "02512519498", 'J', 500234567, "Hospital cercano a la av. libertador de alta capacidad", "German Grateron", "Edf. Hospital Central Universitario 'Antonio Maria Pineda'"),
                new Institucion("C. Clinico Valentina Canabal", "Av. Crispulo Benitez, con Calle Bolivia, Los Libertadores. Barquisitemo. Lara", "02517107700", 'J', 50038392, "Clinica cercana a la intersección de avenidas Libertador y Urdaneta\nDe alto rendimiento y variedad de especialistas", "Dr. Rodolfo Isea", "Centro Clinico Valentina Canabal"),
                new Institucion("Hospital Rotario de Barquisimeto Fundarosa", "Av. Florencio Gimenez. Barquisimeto. Lara", "04127166310", 'J', 50012392, "Hospital privado con relaciones al rotary", "Enabeth Montilva", "Hospital Rotario de Fundarosa", l1),
                new Institucion("Hospital 'Dr. Luis Gomez Lopez'", "Correra 18, con calle 12. Barquisimeto. Lara", "02512524190", 'J', 5007831, "Hospital con variedad de especialistas", "Edson Hernandez", "Hospital 'Dr. Luis Gomez Lopez'")
        );
        ObservableList<Farmacia> listFarmacias = FXCollections.observableArrayList(
                new Farmacia("Farmacia Alegria", "Carrera 18 Con Calle 33 Barquisimeto Lara", "04127600618", 'J', 0, "", "Lisheth Garibaldi", "farmacialegriac.a@hotmail.com", 2, "Independiente", "Dronena", l2),
                new Farmacia("Farmatodo El Parque", "Av. Libertador Con Vereda No. 1 0 Barquisimeto Lara", "02512515656", 'J', 0, "", "Mario", "parque.313@farmatodo.com", 4, "Farmatodo", "Dollder"),
                new Farmacia("Fsi Ciudad Altagracia", "Av. 20 Entre Calles 19 y 20", "04145768840", 'J', 0, "", "Luisa", "farmaignacioaltagracia@gmail.com", 2, "Cadena Regional", "Dronena")
        );

        ObservableList<Evento> listEventos = FXCollections.observableArrayList();
        listEventos.addAll(l1);
        listEventos.addAll(l2);
        listEventos.addAll(l3);

        admin = new Administrador("LiaUCAB", "proyecto1234", "Caracas", "lia@gmail.com");
        usuario = new ATM("PruebaATM", "1234", "Caracas", "sandro@gmail.com", admin);
        listaAtm = new ArrayList<>();
        listaAtm.add(usuario);

        admin.setAtmList(listaAtm);

        for (Evento evento: listEventos) {
            usuario.registrarEvento(evento);
        }
        for (Medico med: listMedicos) {
            usuario.registrarCliente(med);
        }
        for (Institucion inst: listInstituciones) {
            usuario.registrarCliente(inst);
        }
        for (Farmacia farma: listFarmacias) {
            usuario.registrarCliente(farma);
        }

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