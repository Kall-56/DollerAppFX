package com.ucab.dollerappfx;

import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablasController {
    
    public static <T> void establecerTipoTabla(TableView<T> tabla, Class<T> clase, ObservableList<T> lista) {
        tabla.setItems(lista);
        tabla.getColumns().clear();

        TableColumn<T, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));

        TableColumn<T, String> identidadCol = new TableColumn<>("Documento Identidad");
        identidadCol.setCellValueFactory(new PropertyValueFactory<>("docIdentidad"));

        TableColumn<T, String> numeroCol = new TableColumn<>("Número TLF");
        numeroCol.setCellValueFactory(new PropertyValueFactory<>("numTLF"));

        TableColumn<T, String> direccionCol = new TableColumn<>("Dirección");
        direccionCol.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tabla.getColumns().add(nombreCol);
        tabla.getColumns().add(identidadCol);
        tabla.getColumns().add(numeroCol);
        tabla.getColumns().add(direccionCol);

        // Establecer las columnas segun la Clase correspondiente
        if (clase == Medico.class) {
            TableColumn<T, String> emailCol = new TableColumn<>("Correo");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            
            TableColumn<T, String> especialidadCol = new TableColumn<>("Especialidad");
            especialidadCol.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

            TableColumn<T, String> frecuenciaCol = new TableColumn<>("Frecuencia");
            frecuenciaCol.setCellValueFactory(new PropertyValueFactory<>("frecuencia"));

            TableColumn<T, String> diasCol = new TableColumn<>("Días de Visita");
            diasCol.setCellValueFactory(new PropertyValueFactory<>("diasVisita"));

            TableColumn<T, String> horarioCol = new TableColumn<>("Horario");
            horarioCol.setCellValueFactory(new PropertyValueFactory<>("horario"));

            TableColumn<T, String> formatoCol = new TableColumn<>("Formato");
            formatoCol.setCellValueFactory(new PropertyValueFactory<>("formato"));

            tabla.getColumns().add(emailCol);
            tabla.getColumns().add(especialidadCol);
            tabla.getColumns().add(frecuenciaCol);
            tabla.getColumns().add(diasCol);
            tabla.getColumns().add(horarioCol);
            tabla.getColumns().add(formatoCol);
        }
        else if (clase == Institucion.class) {
            TableColumn<T, String> edfCol = new TableColumn<>("Edificio Facultad");
            edfCol.setCellValueFactory(new PropertyValueFactory<>("edfFacultad"));

            TableColumn<T, String> personaCol = new TableColumn<>("Persona Contacto");
            personaCol.setCellValueFactory(new PropertyValueFactory<>("personaContacto"));

            tabla.getColumns().add(personaCol);
            tabla.getColumns().add(edfCol);
        }
        else if (clase == Farmacia.class) {
            TableColumn<T, String> personaCol = new TableColumn<>("Persona Contacto");
            personaCol.setCellValueFactory(new PropertyValueFactory<>("personaContacto"));

            TableColumn<T, String> emailCol = new TableColumn<>("Correo");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<T, String> frecuenciaCol = new TableColumn<>("Frecuencia");
            frecuenciaCol.setCellValueFactory(new PropertyValueFactory<>("frecuencia"));

            TableColumn<T, String> cadenaCol = new TableColumn<>("Cadena");
            cadenaCol.setCellValueFactory(new PropertyValueFactory<>("cadena"));

            TableColumn<T, String> drogueriaCol = new TableColumn<>("Droguería");
            drogueriaCol.setCellValueFactory(new PropertyValueFactory<>("drogueria"));

            tabla.getColumns().add(emailCol);
            tabla.getColumns().add(personaCol);
            tabla.getColumns().add(frecuenciaCol);
            tabla.getColumns().add(cadenaCol);
            tabla.getColumns().add(drogueriaCol);
        }
        tabla.refresh();
    }
}
