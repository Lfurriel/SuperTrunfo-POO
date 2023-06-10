package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    public AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setOnMouseClicked(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));

    }
}