package com.ucab.dollerappfx;

import java.io.IOException;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;


public class MenuPrincipalController {

    @FXML
    private Pane btLogo;
    
    @FXML
    private Pane btLogOut;
    
    @FXML
    private Button btMedic;

    @FXML
    private Pane btMedicS;
    
    @FXML
    private Circle bgCircleMedicS;

    @FXML
    private StackPane pnContenido;

    @FXML
    private Pane pnSubMenuPrincipal;
    
    @FXML
    private AnchorPane pnSubMenuClientes;

    @FXML
    private TableView<Cliente> tbClientesRecientes;

    @FXML
    private TableView<Evento> tbEventosMenu;

    private MenuClientesController controllerClientes; // Controlador de Menu Clientes

    private ObservableList<Medico> listMedicos;

    private ObservableList<Institucion> listInstituciones;

    private ObservableList<Farmacia> listFarmacias;

    @FXML
    void btLogoClicked(MouseEvent event) {
        pnSubMenuPrincipal.setVisible(true);
        pnSubMenuClientes.setVisible(false);
        bgCircleMedicS.setVisible(false);
    }
    
    @FXML
    void btMedicPressed(MouseEvent event) {
        cambio(true, false);
        controllerClientes.cambio(false, true);
        bgCircleMedicS.setVisible(true);

        controllerClientes.establecerClase(Medico.class, listMedicos);
    }

    @FXML
    void btMedicSExited(MouseEvent event) {
        // Hay que cambiar la lógica, porque las 3 clases usan pnSubMenuClientes
        if (!pnSubMenuClientes.isVisible()){
            bgCircleMedicS.setVisible(false);
        }
    }

    @FXML
    void btMedicSEntered(MouseEvent event) {
        bgCircleMedicS.setVisible(true);
    }

    @FXML
    void btInstPressed(MouseEvent event) {
        cambio(true, false);
        controllerClientes.cambio(false, true);

        controllerClientes.establecerClase(Institucion.class, listInstituciones);
    }

    public void cambio(boolean a, boolean b) {
        pnSubMenuClientes.setVisible(a);
        pnSubMenuPrincipal.setVisible(b);
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClientes.fxml"));
            pnSubMenuClientes = loader.load();
            controllerClientes = loader.getController();

            pnSubMenuClientes.setVisible(false);
            pnContenido.getChildren().add(pnSubMenuClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hay que cargar o crear las listas (Aqui estoy usando unos de prueba)
        listMedicos = FXCollections.observableArrayList(
                new Medico("Ale", "Por ahi", 424, 'V', 30282309, "No se", "Huesos", "a@gmail.com", 0, "Lunes-Viernes", "9-5", "No se")
        );
        listInstituciones = FXCollections.observableArrayList(
                new Institucion("Manu", "Por alla", 412, 'V', 31423309, "No se", "Alguien", "Ciencias"),
                new Institucion("Pepe", "Por aca", 426, 'V', 10544904, "No se", "Otro alguien", "Humanidades")
        );
        listFarmacias = FXCollections.observableArrayList(
                new Farmacia("Alex", "Por aqui", 414, 'J', 11225210, "No se", "Un extraño", "b@gmail.com", 1, "Perpetua", "Acetaminofen")
        );

        // Creacion de la tabla MultiClase
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(listMedicos);
        clientes.addAll(listInstituciones);
        clientes.addAll(listFarmacias);
        TablasController.establecerTipoTabla(tbClientesRecientes, Cliente.class, clientes);
    }
}
