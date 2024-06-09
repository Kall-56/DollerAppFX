package com.ucab.dollerappfx;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ValidacionesController {

    public static boolean validarCampos(TextField campo, String regex, Label lbError) {
        String valor = campo.getText();
        if (valor == null || valor.trim().isEmpty()) {
            campo.setStyle("-fx-border-color: red;"+"-fx-border-radius: 3;"+"-fx-focus-color: red;"+"-fx-shadow-highlight-color: red;");
            return false;
        } else if (!valor.matches(regex)) {
            campo.setStyle("-fx-border-color: red;"+"-fx-border-radius: 3;"+"-fx-focus-color: red;"+"-fx-shadow-highlight-color: red;");
            lbError.setVisible(true);
            return false;
        } else {
            campo.setStyle("-fx-border-color: null");
            lbError.setVisible(false);
            return true;
        }
    }
    
    public static boolean validarContrasena(TextField campo, TextField contrasena, Label lbError1, Label lbError2){
        String valor = campo.getText();
        String valorReal = contrasena.getText();
        if (valor == null || valor.trim().isEmpty()) {
            campo.setStyle("-fx-border-color: red;"+"-fx-border-radius: 3;"+"-fx-focus-color: red;"+"-fx-shadow-highlight-color: red;");
            return false;
        } else if (valor == null ? valorReal != null : !valor.equals(valorReal)) {
            campo.setStyle("-fx-border-color: red;"+"-fx-border-radius: 3;"+"-fx-focus-color: red;"+"-fx-shadow-highlight-color: red;");
            lbError1.setVisible(true);
            lbError2.setVisible(true);
            return false;
        } else {
            campo.setStyle("-fx-border-color: null");
            lbError1.setVisible(false);
            lbError2.setVisible(false);
            return true;
        }
    }

    public static boolean validarCampos(TextArea campo, String regex) {
        String valor = campo.getText();
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        } else if (!valor.matches(regex)) {
            campo.setStyle("-fx-text-fill: RED");
            return false;
        } else {
            return true;
        }
    }
}
