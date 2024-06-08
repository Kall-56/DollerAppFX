package com.ucab.dollerappfx;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ValidacionesController {

    public static boolean validarCampos(TextField campo, String regex) {
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
