package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public Button jogar;
    @FXML
    public Button novoJogador;
    @FXML
    public Button novaCarta;
    @FXML
    public Button verCartas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jogar.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", false));

        novoJogador.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));

        novaCarta.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", true));

        verCartas.setOnAction(event -> DBUtils.changeScene(event, "verCartas.fxml", "CARTAS"));
    }
}
