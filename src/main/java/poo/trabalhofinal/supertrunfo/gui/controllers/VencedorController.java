package poo.trabalhofinal.supertrunfo.gui.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.sql.SQLException;

/**
 * <h1>Classe VencedorController<h1/>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de vencedor</i> e o programa.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  Aqui, mostra o nome do jogador vencedor e atualiza os dados (pontuação) de ambos os jogadores.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initialize</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class VencedorController {
    /**
     *
     */
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
