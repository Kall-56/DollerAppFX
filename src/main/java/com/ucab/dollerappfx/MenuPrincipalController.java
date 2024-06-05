package com.ucab.dollerappfx;

import java.io.IOException;

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
    private TableView<?> tbClientesRecientes;

    @FXML
    private TableView<?> tbEventosMenu;

    @FXML
    void btLogoClicked(MouseEvent event) {
        pnSubMenuPrincipal.setVisible(true);
        pnSubMenuClientes.setVisible(false);
        bgCircleMedicS.setVisible(false);
    }
    
    @FXML
    void btMedicPressed(MouseEvent event) {
        pnSubMenuClientes.setVisible(true);
        pnSubMenuPrincipal.setVisible(false);
        bgCircleMedicS.setVisible(true);
    }

    @FXML
    void btMedicSExited(MouseEvent event) {
        if (!pnSubMenuClientes.isVisible()){
            bgCircleMedicS.setVisible(false);
        }
    }

    @FXML
    void btMedicSEntered(MouseEvent event) {
        bgCircleMedicS.setVisible(true);
    }
    
    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClientes.fxml"));
            pnSubMenuClientes = loader.load();
            pnSubMenuClientes.setVisible(false);
            pnContenido.getChildren().add(pnSubMenuClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
