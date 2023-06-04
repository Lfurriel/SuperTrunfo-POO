package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class OpcaoController implements Initializable {
    @FXML
    public Button personagem;
    @FXML
    public Button gatos;
    @FXML
    public Button linguagemProg;
    private boolean cadastro;

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personagem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cadastro)
                    DBUtils.changeScene(event, "cadastroCarta.fxml", "NOVA CARTA", "Personagem");
                else
                    DBUtils.changeScene(event, "login.fxml", "LOGIN");
            }
        });

        gatos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "LOGIN");
            }
        });

        linguagemProg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "LOGIN");
            }
        });
    }
}
