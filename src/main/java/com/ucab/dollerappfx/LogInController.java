package com.ucab.dollerappfx;

import java.io.IOException;
import java.util.ArrayList;

import Classes.*;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LogInController {
//--------------------------Panel de LogIn------------------------------------//
    @FXML
    private Pane pnUserLogIn;
    @FXML
    private TextField fldUserName;
    @FXML
    private PasswordField fldUserPassword;
    @FXML
    private Label fldPasswordForgot;
    @FXML
    private Pane btUserLogIn;
    @FXML
    private Label lbSiguiente;
    @FXML
    private Pane btUserCreate;
    @FXML
    private Label lbCrearCuenta;
    
//--------------------------Panel de crear usuario----------------------------//
    @FXML
    private Pane pnUserCreate;
    @FXML
    private TextField fldUserNameCrt;
    @FXML
    private TextField fldUserZoneCrt;
    @FXML
    private TextField fldUserEmailCrt;
    @FXML
    private PasswordField fldUserPasswordCrt;
    @FXML
    private PasswordField fldUserPasswordConfirm;
    @FXML
    private Pane btUserCreateNew;
    @FXML
    private Label lbCrearNew;
    @FXML
    private Pane btBack;
    @FXML
    private Label lbVolver;
    @FXML
    private Label lbUserNameError;
    @FXML
    private Label lbUserZoneError;
     @FXML
    private Label lbUserEmailError;
     @FXML
    private Label lbUserPasswordError;
    @FXML
    private Label lbUserPasswordConfirmError;
    
//--------------------------Propiedades Usuario-------------------------------//
    private ArrayList<ATM> listaAtm;
    public static ATM usuario; // Al cargar el ATM, la aplicacion puede usarla siempre hasta que se cambie a otro
    public static Administrador admin;
//--------------------------Metodos del fxml----------------------------------//
//---------------------------Panel de LogIn----------------------------------------//
    
    @FXML
    void btLogInClicked(MouseEvent event) throws IOException {
          if (fldUserName.getText().equals(admin.getNomUsuario())) {
              App.setRoot("MenuAdmin");
          } else {
              App.setRoot("MenuPrincipal");
          }
//        if (fldUserName.getText().equals(usuario.getNomUsuario()) && fldUserPassword.getText().equals(usuario.getClave())) {

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
    void fldForgotPressed(MouseEvent event) {

    }
    @FXML
    void fldForgotEntered(MouseEvent event) {
        fldPasswordForgot.setTextFill(App.aguamarina);
    }

    @FXML
    void fldForgotExited(MouseEvent event) {
        fldPasswordForgot.setTextFill(App.naranja);
    }
    
//--------------------------Panel de crear usuario---------------------------------//
    @FXML
    void btCreateNewClicked(MouseEvent event) {
        boolean nombreValido = ValidacionesController.validarCampos(fldUserNameCrt, "[a-zA-Z0-9]+",lbUserNameError);
        boolean emailValido = ValidacionesController.validarCampos(fldUserEmailCrt, "^[A-Za-z0-9+_.-]+@(.+)$",lbUserEmailError);
        boolean territorioValido = ValidacionesController.validarCampos(fldUserZoneCrt, "[a-zA-Z ]+",lbUserZoneError);
        boolean claveValido = ValidacionesController.validarCampos(fldUserPasswordCrt, "[a-zA-Z0-9_.-]+",lbUserPasswordError);
        boolean claveConfirm = ValidacionesController.validarContrasena(fldUserPasswordConfirm, fldUserPasswordCrt,lbUserPasswordError,lbUserPasswordConfirmError);

        boolean [] atributosValidos = {nombreValido, emailValido, territorioValido, claveValido, claveConfirm};
        boolean valido = true;
        for (boolean atributo: atributosValidos) {
            if (!atributo) {
                valido = false;
            }
        }
        if (valido && (fldUserPasswordCrt.getText().equals(fldUserPasswordConfirm.getText()))) {
            admin.registrarATM(new ATM(fldUserNameCrt.getText(),
                                       fldUserPasswordCrt.getText(),
                                       fldUserZoneCrt.getText(),
                                       fldUserEmailCrt.getText(),
                                       admin));
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
//----------------------------------------------------------------------------//

    public void initialize() {
        admin = new Administrador("LiaUCAB", "proyecto1234", "Caracas", "lia@gmail.com");
        usuario = new ATM("Sandro", "1234", "Caracas", "sandro@gmail.com", admin);
        listaAtm = new ArrayList<>();
        listaAtm.add(usuario);

        admin.setAtmList(listaAtm);

        for (Evento evento: App.listEventos) {
            usuario.registrarEvento(evento);
        }
        for (Medico med: App.listMedicos) {
            usuario.registrarCliente(med);
        }
        for (Institucion inst: App.listInstituciones) {
            usuario.registrarCliente(inst);
        }
        for (Farmacia farma: App.listFarmacias) {
            usuario.registrarCliente(farma);
        }
        //------------------------------------------------------------------------//
        fldUserNameCrt.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldUserNameCrt, "([a-zA-Z0-9]+).{4,25}",lbUserNameError);
            }
        });
        fldUserZoneCrt.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldUserZoneCrt, "[a-zA-Z ]+",lbUserZoneError);
            }
        });
        fldUserEmailCrt.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldUserEmailCrt, "^[A-Za-z0-9+_.-]+@(.+).{5,320}$",lbUserEmailError);
            }
        });
        fldUserPasswordCrt.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldUserPasswordCrt, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*_)(?!.* ).{8,16}$",lbUserPasswordError);
            }
        });
    }
}
