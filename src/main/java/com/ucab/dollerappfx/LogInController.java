package com.ucab.dollerappfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.ATM;
import Classes.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LogInController {

    @FXML
    private Pane btBack;
    
    @FXML
    private Label lbVolver;

    @FXML
    private Pane btUserCreate;
    
    @FXML
    private Label lbCrearCuenta;

    @FXML
    private Pane btUserCreateNew;
    
    @FXML
    private Label lbCrearNew;

    @FXML
    private Pane btUserLogIn;
    
    @FXML
    private Label lbSiguiente;

    @FXML
    private Label fldPasswordForgot;

    @FXML
    private TextField fldUserEmailCrt;

    @FXML
    private TextField fldUserName;

    @FXML
    private TextField fldUserNameCrt;

    @FXML
    private PasswordField fldUserPassword;

    @FXML
    private PasswordField fldUserPasswordConfirm;

    @FXML
    private PasswordField fldUserPasswordCrt;

    @FXML
    private TextField fldUserZoneCrt;

    @FXML
    private Pane pnUserCreate;

    @FXML
    private Pane pnUserLogIn;

    private ArrayList<ATM> listaAtm;

    public static ATM usuario; // Al cargar el ATM, la aplicacion puede usarla siempre hasta que se cambie a otro
    
    @FXML
    void btBackClicked(MouseEvent event) {
        pnUserLogIn.setVisible(true);
        pnUserCreate.setVisible(false);
    }

    @FXML
    void btBackEntered(MouseEvent event) {
        btBack.setStyle("-fx-background-color: #6b6b6b;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 5;");
        lbVolver.setTextFill(Color.WHITE);
    }

    @FXML
    void btBackExited(MouseEvent event) {
        btBack.setStyle("-fx-background-color: white;"+"-fx-border-color: #6b6b6b;"+"-fx-background-radius: 5;"+"-fx-border-radius: 5;");
        lbVolver.setTextFill(Color.web("#6b6b6b"));
    }

    @FXML
    void btCreateClicked(MouseEvent event) {
        pnUserCreate.setVisible(true);
        pnUserLogIn.setVisible(false);
    }

    @FXML
    void btCreateEntered(MouseEvent event) {
        btUserCreate.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 5;");
        lbCrearCuenta.setTextFill(Color.WHITE);
    }

    @FXML
    void btCreateExited(MouseEvent event) {
        btUserCreate.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 5;"+"-fx-border-radius: 5;");
        lbCrearCuenta.setTextFill(Color.web("#FF7B52"));
    }

    @FXML
    void btCreateNewClicked(MouseEvent event) {
        boolean nombreValido = ValidacionesController.validarCampos(fldUserNameCrt, "[a-zA-Z0-9]+");
        boolean emailValido = ValidacionesController.validarCampos(fldUserEmailCrt, "^[A-Za-z0-9+_.-]+@(.+)$");
        boolean territorioValido = ValidacionesController.validarCampos(fldUserZoneCrt, "[a-zA-Z ]+");
        boolean claveValido = ValidacionesController.validarCampos(fldUserPasswordCrt, "[a-zA-Z0-9_.-]+");

        boolean [] atributosValidos = {nombreValido, emailValido, territorioValido, claveValido};
        boolean valido = true;
        for (boolean atributo: atributosValidos) {
            if (!atributo) {
                valido = false;
                break; // Tal vez haya que quitarlo
            }
        }
        if (valido && (fldUserPasswordCrt.getText().equals(fldUserPasswordConfirm.getText()))) {
            listaAtm.add(new ATM(fldUserNameCrt.getText(),
                                 fldUserZoneCrt.getText(),
                                 fldUserEmailCrt.getText(),
                                 fldUserPasswordCrt.getText(),
                                 new Administrador()));
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("");
            alerta.setContentText("Revise que no haya ningún campo con formato erróneo");
            alerta.setTitle("Error");
            alerta.showAndWait();
        }
    }

    @FXML
    void btCreateNewEntered(MouseEvent event) {
        btUserCreateNew.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 5;");
        lbCrearNew.setTextFill(Color.WHITE);
    }

    @FXML
    void btCreateNewExited(MouseEvent event) {
        btUserCreateNew.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 5;"+"-fx-border-radius: 5;");
        lbCrearNew.setTextFill(Color.web("#FF7B52"));
    }

    @FXML
    void btLogInClicked(MouseEvent event) throws IOException {
//        if (fldUserName.getText().equals(usuario.getNomUsuario()) && fldUserPassword.getText().equals(usuario.getClave())) {
            App.setRoot("MenuPrincipal");
//        }
//        else {
//            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//            alerta.setTitle("Datos inválidos");
//            alerta.setHeaderText("Usuario y/o contraseña incorrectos");
//            alerta.showAndWait();
//        }
    }

    @FXML
    void btLogInEntered(MouseEvent event) {
        btUserLogIn.setStyle("-fx-background-color: #ff8e37;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 5;");
        lbSiguiente.setTextFill(Color.WHITE);
    }

    @FXML
    void btLogInExited(MouseEvent event) {
        btUserLogIn.setStyle("-fx-background-color: white;"+"-fx-border-color: orange;"+"-fx-background-radius: 5;"+"-fx-border-radius: 5;");
        lbSiguiente.setTextFill(Color.web("#ff8e37"));
    }

    @FXML
    void fldForgotEntered(MouseEvent event) {
        fldPasswordForgot.setTextFill(App.aguamarina);
    }

    @FXML
    void fldForgotExited(MouseEvent event) {
        fldPasswordForgot.setTextFill(App.naranja);
    }

    @FXML
    void fldForgotPressed(MouseEvent event) {

    }

    public void initialize() {
        usuario = new ATM();
        listaAtm = new ArrayList<>();
        listaAtm.add(usuario);
    }
}
