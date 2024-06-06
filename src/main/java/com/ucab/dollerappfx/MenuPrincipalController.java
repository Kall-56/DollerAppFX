package com.ucab.dollerappfx;

import java.io.IOException;

import Classes.*;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;


public class MenuPrincipalController {

    @FXML
    private Circle bgCircleFarmaS;

    @FXML
    private Circle bgCircleInstS;

    @FXML
    private Circle bgCircleMedicS;

    @FXML
    private Pane btFarmaS;
    
    @FXML
    private Pane btFarma;

    @FXML
    private Pane btInst;

    @FXML
    private Pane btInstS;

    @FXML
    private ImageView btLogOut;

    @FXML
    private Pane btLogo;

    @FXML
    private Pane btMedic;

    @FXML
    private Pane btMedicS;

    @FXML
    private StackPane pnContenido;

    @FXML
    private Pane pnSideBar;

    @FXML
    private AnchorPane pnSubMenuClientes;

    @FXML
    private AnchorPane pnSubMenuPrincipal;

    @FXML
    private TableView<Cliente> tbClientesRecientes;

    @FXML
    private TableView<Evento> tbEventosMenu;

    private MenuClientesController controllerClientes; // Controlador de Menu Clientes

    private ObservableList<Medico> listMedicos;

    private ObservableList<Institucion> listInstituciones;

    private ObservableList<Farmacia> listFarmacias;
    
    private double lowOpacity = 0.35;
    private double maxOpacity = 1;

    @FXML
    void btLogoClicked(MouseEvent event) {
        visibilityChange(false,true);
        bgCircleMedicS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
    }
    
    @FXML
    void btMedicPressed(MouseEvent event) {
        visibilityChange(true, false);
        controllerClientes.cambio(false, true);
        bgCircleMedicS.setOpacity(maxOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Medico.class, listMedicos);
    }

    @FXML
    void btMedicSExited(MouseEvent event) {
        if (!pnSubMenuClientes.isVisible() || !"Médicos".equals(controllerClientes.getClientesLabel())){
            bgCircleMedicS.setOpacity(lowOpacity);
        }
    }

    @FXML
    void btMedicSEntered(MouseEvent event) {
        bgCircleMedicS.setOpacity(maxOpacity);
    }
    
    @FXML
    void btFarmaPressed(MouseEvent event) {
        visibilityChange(true, false);
        controllerClientes.cambio(false, true);
        bgCircleFarmaS.setOpacity(maxOpacity);
        bgCircleInstS.setOpacity(lowOpacity);
        bgCircleMedicS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Farmacia.class, listFarmacias);
    }

    @FXML
    void btFarmaSEntered(MouseEvent event) {
        bgCircleFarmaS.setOpacity(maxOpacity);
    }

    @FXML
    void btFarmaSExited(MouseEvent event) {
        if (!pnSubMenuClientes.isVisible() || !"Farmacias".equals(controllerClientes.getClientesLabel())){
            bgCircleFarmaS.setOpacity(lowOpacity);
        }
    }
    
    @FXML
    void btInstPressed(MouseEvent event) {
        visibilityChange(true, false);
        controllerClientes.cambio(false, true);
        bgCircleInstS.setOpacity(maxOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleMedicS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Institucion.class, listInstituciones);
    }

    @FXML
    void btInstSEntered(MouseEvent event) {
        bgCircleInstS.setOpacity(maxOpacity);
    }

    @FXML
    void btInstSExited(MouseEvent event) {
        if (!pnSubMenuClientes.isVisible() || !"Instituciones".equals(controllerClientes.getClientesLabel())){
            bgCircleInstS.setOpacity(lowOpacity);
        }
    }
    
    @FXML
    void btLogOutClicked(MouseEvent event) throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Seguro que quiere cerrar sesion?");
        ButtonType confirmButton = ButtonType.OK;
        ButtonType cancelButton = ButtonType.CANCEL;

        alerta.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alerta.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            App.setRoot("LogIn");
        }
        
    }
    
    public void visibilityChange(boolean a, boolean b) {
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
