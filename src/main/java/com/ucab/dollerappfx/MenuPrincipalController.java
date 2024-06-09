package com.ucab.dollerappfx;

import java.io.IOException;

import Classes.*;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private AnchorPane pnSubMenuPrincipal;

    @FXML
    private TableView<Cliente> tbClientesRecientes;

    @FXML
    private TableView<Evento> tbEventosMenu;

    @FXML
    private AnchorPane pnSubMenuClientes;

    private MenuClientesController controllerClientes;

    private final double lowOpacity = 0.35;
    private final double maxOpacity = 1;

    @FXML
    void btLogoClicked(MouseEvent event) {
        visibilityChange(false,true);
        bgCircleMedicS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(App.listMedicos);
        clientes.addAll(App.listInstituciones);
        clientes.addAll(App.listFarmacias);

        tbClientesRecientes.setItems(clientes);
        tbClientesRecientes.refresh();
        tbEventosMenu.refresh();
    }

    @FXML
    void btMedicPressed(MouseEvent event) {
        visibilityChange(true, false);
        controllerClientes.visibilityChange(false, true, false);
        bgCircleMedicS.setOpacity(maxOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Medico.class, App.listMedicos);
        controllerClientes.filtroClientes(FXCollections.observableArrayList(App.listMedicos));
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
        controllerClientes.visibilityChange(false, true, false);
        bgCircleFarmaS.setOpacity(maxOpacity);
        bgCircleInstS.setOpacity(lowOpacity);
        bgCircleMedicS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Farmacia.class, App.listFarmacias);
        controllerClientes.filtroClientes(FXCollections.observableArrayList(App.listFarmacias));
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
        controllerClientes.visibilityChange(false, true, false);
        bgCircleInstS.setOpacity(maxOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleMedicS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Institucion.class, App.listInstituciones);
        controllerClientes.filtroClientes(FXCollections.observableArrayList(App.listInstituciones));
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
        // La Tabla de Eventos
        tbEventosMenu.getColumns().clear();

        TableColumn<Evento, String> nombreCol = new TableColumn<>("Cliente");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        TableColumn<Evento, String> tituloCol = new TableColumn<>("Título Actividad");
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Evento, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Evento, String> semanaCol = new TableColumn<>("Semana");
        semanaCol.setCellValueFactory(new PropertyValueFactory<>("semana"));

        TableColumn<Evento, String> descripCol = new TableColumn<>("Descripción");
        descripCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbEventosMenu.getColumns().add(nombreCol);
        tbEventosMenu.getColumns().add(tituloCol);
        tbEventosMenu.getColumns().add(fechaCol);
        tbEventosMenu.getColumns().add(semanaCol);
        tbEventosMenu.getColumns().add(descripCol);
        tbEventosMenu.setItems(App.listEventos);
        tbEventosMenu.refresh();

        // Creacion de la tabla MultiClase
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(LogInController.usuario.getMedicoList());
        clientes.addAll(LogInController.usuario.getFarmaciaList());
        clientes.addAll(LogInController.usuario.getInstitucionList());
        TablasController.establecerTipoTabla(tbClientesRecientes, Cliente.class, clientes);


    }
}
