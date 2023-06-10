package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VencedorController {
    @FXML
    private Button sair;
    @FXML
    private Label vencedor;

    private final JogadoresRepository<?> jogadoresRepository = new JogadoresRepositoryImpl<>();
    private Jogador jogadorVencedor = DBUtils.getVencedor();
    private Jogador jogadorFracasado = DBUtils.getPerdedor();

    public void setJogadores(Jogador jogadorVencedor, Jogador jogadorFracasado) {
        this.jogadorVencedor = jogadorVencedor;
        this.jogadorFracasado = jogadorFracasado;
    }

    @FXML
    public void initialize() {
        vencedor.setText(jogadorVencedor.getNome().toUpperCase());
        sair.setOnAction(event -> {
            try {
                jogadoresRepository.updateJogadores(jogadorVencedor, jogadorFracasado);
            } catch (SQLException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERRO");
                alerta.setContentText(e.getMessage());
                alerta.showAndWait();
            }
            DBUtils.changeScene(event, "menu.fxml", "MENU");
        });
    }
}
