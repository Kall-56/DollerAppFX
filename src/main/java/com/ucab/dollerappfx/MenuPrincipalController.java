package com.ucab.dollerappfx;

import java.io.IOException;

import Classes.*;

import java.util.ArrayList;
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

    public static ObservableList<Medico> listMedicos;

    public static ObservableList<Institucion> listInstituciones;

    public static ObservableList<Farmacia> listFarmacias;

    public static ObservableList<Evento> listEventos;

    private final double lowOpacity = 0.35;
    private final double maxOpacity = 1;

    @FXML
    void btLogoClicked(MouseEvent event) {
        visibilityChange(false,true);
        bgCircleMedicS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);

        tbClientesRecientes.refresh();
        tbEventosMenu.refresh();
    }

    @FXML
    void btMedicPressed(MouseEvent event) {
        visibilityChange(true, false);
        controllerClientes.cambio(false, true);
        bgCircleMedicS.setOpacity(maxOpacity);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);

        controllerClientes.establecerClase(Medico.class, listMedicos);
        controllerClientes.filtroClientes(FXCollections.observableArrayList(listMedicos));
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
        controllerClientes.filtroClientes(FXCollections.observableArrayList(listFarmacias));
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
        controllerClientes.filtroClientes(FXCollections.observableArrayList(listInstituciones));
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
    void btEventPressed(MouseEvent event) {
        pnSubMenuClientes.setVisible(false);
        pnSubMenuPrincipal.setVisible(false);
        bgCircleFarmaS.setOpacity(lowOpacity);
        bgCircleMedicS.setOpacity(lowOpacity);
        bgCircleInstS.setOpacity(lowOpacity);

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(listMedicos);
        clientes.addAll(listInstituciones);
        clientes.addAll(listFarmacias);
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


        // Hay que cargar o crear las listas (Aqui estoy usando unos de prueba)
        ArrayList<Evento> l1 = new ArrayList<>();
        l1.add(new Evento("Visita Hospitalaria","Dubin Ludmila", "24/05/2024", 2, "Supongamos que"));
        l1.add(new Evento("Visita Hospitalaria", "Dubin Ludmila", "01/10/2024", 4, "Pasarán cosas"));

        ArrayList<Evento> l2 = new ArrayList<>();
        l2.add(new Evento("Actividad Promocional", "Alex", "12/04/24", 1, "Evento intergaláctico"));
        l2.add(new Evento("Actividad Promocional", "Alex", "17/09/24", 3, "Se aprecian cositas"));

        listMedicos = FXCollections.observableArrayList(
                new Medico("Baez Arnaldo", "Avenida Vargas Entre Las Palmas y Caerrera 32", "04143515161", 'V', 7884170, "No se", "Urólogo", "arnaldobaez@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes"),
                new Medico("Dubin Ludmila", "Calle 20 Con Carrera 23 Lara", "18325313408", 'V', 7329907, "No se", "Ginecólogo", "ludmiladubim@gmail.com", 1, "Lun-Mar-Mie-Jue-Vie", "8am-12pm", "Entre pacientes", l1)
        );
        listInstituciones = FXCollections.observableArrayList(
                new Institucion("Manu", "Por alla", "412", 'V', 31423309, "No se", "Alguien", "Ciencias"),
                new Institucion("Pepe", "Por aca", "426", 'V', 10544904, "No se", "Otro alguien", "Humanidades"),
                new Institucion("Pedro", "Un lugar", "412", 'V', 9742912, "No se", "Juanito", "Ingeniería")
        );
        listFarmacias = FXCollections.observableArrayList(
                new Farmacia("Alex", "Por aqui", "414", 'J', 11225210, "No se", "Un extraño", "b@gmail.com", 1, "Perpetua", "Acetaminofen", l2),
                new Farmacia("Monsalve", "SJT", "424", 'V', 32848109, "No se", "Pepito", "diablo@gmail.com", 0, "30 años", "Migren")

        );

        listEventos = FXCollections.observableArrayList();
        listEventos.addAll(l1);
        listEventos.addAll(l2);
        tbEventosMenu.setItems(listEventos);
        tbEventosMenu.refresh();

        for (Evento evento: listEventos) {
            LogInController.usuario.registrarEvento(evento);
        }
        for (Medico med: listMedicos) {
            LogInController.usuario.registrarCliente(med);
        }
        for (Institucion inst: listInstituciones) {
            LogInController.usuario.registrarCliente(inst);
        }
        for (Farmacia farma: listFarmacias) {
            LogInController.usuario.registrarCliente(farma);
        }

        // Creacion de la tabla MultiClase
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(listMedicos);
        clientes.addAll(listInstituciones);
        clientes.addAll(listFarmacias);
        TablasController.establecerTipoTabla(tbClientesRecientes, Cliente.class, clientes);
    }
}
