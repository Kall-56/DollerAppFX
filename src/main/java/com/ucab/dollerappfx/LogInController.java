package com.ucab.dollerappfx;

import java.io.IOException;

import Classes.*;
import ManejadorBD.ManejadorBD;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

//--------------------------Panel de recuperar clave----------------------------//
    @FXML
    private Pane pnUserRecClave;

    @FXML
    private Pane btBackClave;

    @FXML
    private Pane btEnviarCodigo;

    @FXML
    private Button btConfirmNuevaClave;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfClaveNueva;

    @FXML
    private TextField tfClaveNuevaConfirm;

    @FXML
    private TextField tfUser;

    private int codRecuperacion;

//--------------------------Metodos del fxml----------------------------------//
//---------------------------Panel de LogIn----------------------------------------//
    
    @FXML
    void btLogInClicked(MouseEvent event) throws IOException, ClassNotFoundException {
          if (ManejadorBD.VerificarUsuarioADMIN(fldUserName.getText(), fldUserPassword.getText())) {
              App.admin = ManejadorBD.retornarUsuarioADMIN(fldUserName.getText());
              App.setRoot("MenuAdmin");
          } else if (ManejadorBD.verificarUsuarioATM(fldUserName.getText(), fldUserPassword.getText())) {
              App.usuario = ManejadorBD.retornarUsuarioATM(fldUserName.getText());
              App.admin = ManejadorBD.retornarUsuarioADMIN(App.usuario.getGerente());
              App.setRoot("MenuPrincipal");
          } else {
              Alert alerta = new Alert(Alert.AlertType.INFORMATION);
              alerta.setTitle("Datos inválidos");
              alerta.setHeaderText("");
              alerta.setContentText("Usuario y/o contraseña incorrectos");
              alerta.showAndWait();
          }
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
        pnUserLogIn.setVisible(false);
        pnUserCreate.setVisible(false);
        pnUserRecClave.setVisible(true);
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
    void btCreateNewClicked(MouseEvent event) throws ClassNotFoundException {
        boolean nombreValido = ValidacionesController.validarCampos(fldUserNameCrt, "[a-zA-Z0-9]+{1,15}",lbUserNameError);
        boolean emailValido = ValidacionesController.validarCampos(fldUserEmailCrt, "^[A-Za-z0-9+_.-]+@(.+)$",lbUserEmailError);
        boolean territorioValido = ValidacionesController.validarCampos(fldUserZoneCrt, "[a-zA-Z ]+{1,30}",lbUserZoneError);
        boolean claveValido = ValidacionesController.validarCampos(fldUserPasswordCrt, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*_)(?!.* ).{8,16}$",lbUserPasswordError);
        boolean claveConfirm = ValidacionesController.validarContrasena(fldUserPasswordConfirm, fldUserPasswordCrt,lbUserPasswordError,lbUserPasswordConfirmError);

        boolean [] atributosValidos = {nombreValido, emailValido, territorioValido, claveValido, claveConfirm};
        boolean valido = true;
        for (boolean atributo: atributosValidos) {
            if (!atributo) {
                valido = false;
            }
        }
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("");
        if (valido && (fldUserPasswordCrt.getText().equals(fldUserPasswordConfirm.getText()))) {
            //////////////////////////////////////////
            ManejadorBD.AgregarUsuarioABaseDeDatos(fldUserNameCrt.getText(), fldUserPasswordCrt.getText(), fldUserZoneCrt.getText(), fldUserEmailCrt.getText(), "LIAUCAB");
            pnUserLogIn.setVisible(true);
            pnUserCreate.setVisible(false);

            alerta.setContentText("ATM creado con éxito");
            alerta.setTitle("ATM Creado");
        } else {
            alerta.setContentText("Revise que no haya ningún campo con formato erróneo");
            alerta.setTitle("Error");
        }
        alerta.showAndWait();
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

//--------------------------Panel de recuperar clave----------------------------//
    @FXML
    void btBackClavePressed(MouseEvent event) {
        pnUserLogIn.setVisible(true);
        pnUserRecClave.setVisible(false);

        codRecuperacion = -101;
    }

    @FXML
    private void btEnviarCodigoPressed() {
        // Max 999999 - Min 100000
        int randomNum = 100000 + (int)(Math.random() * ((999999 - 100000) + 1));
        codRecuperacion = randomNum;

        // Crear una tarea para el envío de correo
        Task<Void> emailTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                ATM atm = ManejadorBD.retornarUsuarioATM(tfUser.getText());

                EmailController correo = new EmailController();
                correo.createEmail(atm.getEmail(), randomNum);
                correo.sendEmail();
                return null;
            }

            @Override
            protected void succeeded() {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Código de recuperación enviado");
                alerta.setHeaderText("");
                alerta.setContentText("Correo enviado con éxito");
                alerta.showAndWait();

                btConfirmNuevaClave.setDisable(false);
                tfClaveNueva.setDisable(false);
                tfClaveNuevaConfirm.setDisable(false);
                tfCodigo.setDisable(false);
            }

            @Override
            protected void failed() {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error en los datos");
                alerta.setHeaderText("");
                alerta.setContentText("Verifique el usuario introducido");
                alerta.showAndWait();
                getException().printStackTrace();
            }
        };

        // Enviar el correo en un nuevo hilo
        Thread email = new Thread(emailTask);
        email.start();
    }

    @FXML
    void btConfirmarClave(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        boolean codigoValido = ValidacionesController.validarCampos(tfCodigo, "^[0-9]{6}$", lbUserPasswordError);
        if (codigoValido && (Integer.parseInt(tfCodigo.getText()) == codRecuperacion)) {
            boolean claveValido = ValidacionesController.validarCampos(tfClaveNueva, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*_)(?!.* ).{8,16}$", lbUserPasswordError);
            if (claveValido && (tfClaveNueva.getText().equals(tfClaveNuevaConfirm.getText()))) {

                // Que el ManejadorBD cambie la clave

                alerta.setTitle("Cambio de clave exitoso");
                alerta.setHeaderText("");
                alerta.setContentText("La clave del usuario ha sido modificado exitosamente");

                pnUserLogIn.setVisible(true);
                pnUserRecClave.setVisible(false);
            } else {
                alerta.setTitle("Error en la clave");
                alerta.setHeaderText("");
                alerta.setContentText("Verifique que la clave nueva sea válida");
            }
        } else {
            alerta.setTitle("Código de recuperación erróneo");
            alerta.setHeaderText("");
            alerta.setContentText("Verifique el código introducido");

        }
        alerta.showAndWait();
    }
//----------------------------------------------------------------------------//

    public void initialize() {
        pnUserLogIn.setVisible(true);
        pnUserCreate.setVisible(false);
        pnUserRecClave.setVisible(false);

        tfCodigo.setDisable(true);
        tfClaveNueva.setDisable(true);
        tfClaveNuevaConfirm.setDisable(true);
        btConfirmNuevaClave.setDisable(true);

        pnUserCreate.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                fldUserNameCrt.clear();
                fldUserNameCrt.setStyle("");
                lbUserNameError.setVisible(false);
                fldUserZoneCrt.clear();
                fldUserZoneCrt.setStyle("");
                lbUserZoneError.setVisible(false);
                fldUserPasswordCrt.clear();
                fldUserPasswordCrt.setStyle("");
                lbUserPasswordError.setVisible(false);
                fldUserPasswordConfirm.clear();
                fldUserPasswordConfirm.setStyle("");
                lbUserPasswordConfirmError.setVisible(false);
                fldUserEmailCrt.clear();
                fldUserEmailCrt.setStyle("");
                lbUserEmailError.setVisible(false);
            }
        });

        pnUserRecClave.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tfUser.clear();
                tfCodigo.clear();
                tfCodigo.setDisable(true);
                tfClaveNueva.clear();
                tfClaveNueva.setDisable(true);
                tfClaveNuevaConfirm.clear();
                tfClaveNuevaConfirm.setDisable(true);
                btConfirmNuevaClave.setDisable(true);
                codRecuperacion = -101;
            }
        });

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
