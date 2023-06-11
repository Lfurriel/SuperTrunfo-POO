package poo.trabalhofinal.supertrunfo.gui.controllers;

import java.awt.Desktop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
    @FXML
    public Button regras;
    @FXML
    public Label alerta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jogar.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", false));

        novoJogador.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));

        novaCarta.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", true));

        verCartas.setOnAction(event -> DBUtils.changeScene(event, "verCartas.fxml", "CARTAS"));

        regras.setOnAction(event -> abrirPDF());
    }

    private void abrirPDF() {
        System.out.println("aaaaa");
        String url ="https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                if (desktop.isSupported(Desktop.Action.BROWSE))
                    desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                alerta.setText("Não foi possível abrir o url");
            }
        } else
            alerta.setText("Não foi possível abrir o url");
    }
}
