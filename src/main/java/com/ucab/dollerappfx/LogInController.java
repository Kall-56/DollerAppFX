package com.ucab.dollerappfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LogInController {

    @FXML
    private Pane btBack;

    @FXML
    private Pane btUserCreate;

    @FXML
    private Pane btUserCreateNew;

    @FXML
    private Pane btUserLogIn;

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

    @FXML
    void btBackClicked(MouseEvent event) {
        pnUserLogIn.toFront();
        pnUserLogIn.setVisible(true);
        pnUserCreate.setVisible(false);
    }

    @FXML
    void btBackEntered(MouseEvent event) {

    }

    @FXML
    void btBackExited(MouseEvent event) {

    }

    @FXML
    void btCreateClicked(MouseEvent event) {
        pnUserCreate.toFront();
        pnUserCreate.setVisible(true);
        pnUserLogIn.setVisible(false);
    }

    @FXML
    void btCreateEntered(MouseEvent event) {

    }

    @FXML
    void btCreateExited(MouseEvent event) {

    }

    @FXML
    void btCreateNewClicked(MouseEvent event) {

    }

    @FXML
    void btCreateNewEntered(MouseEvent event) {

    }

    @FXML
    void btCreateNewExited(MouseEvent event) {

    }

    @FXML
    void btLogInClicked(MouseEvent event) throws IOException {
        App.setRoot("MenuPrincipal");
    }

    @FXML
    void btLogInEntered(MouseEvent event) {

    }

    @FXML
    void btLogInExited(MouseEvent event) {

    }

    @FXML
    void fldForgotEntered(MouseEvent event) {

    }

    @FXML
    void fldForgotExited(MouseEvent event) {

    }

    @FXML
    void fldForgotPressed(MouseEvent event) {

    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
