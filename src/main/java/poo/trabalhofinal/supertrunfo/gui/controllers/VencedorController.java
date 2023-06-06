package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class VencedorController implements Initializable {
    @FXML
    private Button sair;
    @FXML
    private Label vencedor;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vencedor.setText("");
        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            }
        });
    }
}
