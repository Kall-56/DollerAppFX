package com.ucab.dollerappfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class MenuPrincipalController {

    @FXML
    private Pane btMedicS;

    @FXML
    private Button btMeds;

    @FXML
    private StackPane pnContenido;

    @FXML
    private Pane pnSubMenuPrincipal;

    @FXML
    private TableView<?> tbClientesRecientes;

    @FXML
    private TableView<?> tbEventosMenu;

    private AnchorPane menuMeds;

    private AnchorPane menuFarms;

    private AnchorPane menuInst;

    @FXML
    void mostrarMeds(ActionEvent event) {
        menuMeds.setVisible(true);
        pnSubMenuPrincipal.setVisible(false);
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClientes.fxml"));
            menuMeds = loader.load();
            menuMeds.setVisible(false);
            pnContenido.getChildren().add(menuMeds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
