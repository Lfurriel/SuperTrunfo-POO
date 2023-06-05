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
    private boolean cadastro;

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(cadastro);
        jogar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES");
            }
        });

        novoJogador.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO");
            }
        });

        novaCarta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", true);
            }
        });

        verCartas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "verCartas.fxml", "CARTAS");
            }
        });
    }
}
