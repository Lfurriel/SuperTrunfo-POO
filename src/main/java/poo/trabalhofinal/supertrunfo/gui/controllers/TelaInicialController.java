package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController {

    @FXML
    private Label label;

    @FXML
    private void handleMouseClick(MouseEvent event) {
        System.out.println("Apertou (mouse)");
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        System.out.println("Apertou (teclado)");
    }

}
