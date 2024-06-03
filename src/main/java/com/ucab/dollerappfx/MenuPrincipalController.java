package com.ucab.dollerappfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class MenuPrincipalController {
    
    @FXML
    private Pane btMedicS;

    @FXML
    private Pane pnContenido;

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SubMenuPrincipal.fxml"));
            AnchorPane subMenuPrincipal = loader.load();
            pnContenido.getChildren().setAll(subMenuPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }    
    
}
