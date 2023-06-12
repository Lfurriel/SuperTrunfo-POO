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
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class VencedorController {
    /**
     * Elemento FXML que representa um botão, que ao ser clicado, permite ao usuário sair da tela de vencedor.
     */
    @FXML
    private Button sair;
    /**
     * Elemento FXML Label, que mostra o nome do vencedor na tela.
     */
    @FXML
    private Label vencedor;
    /**
     * Atributo privado que conecta ao banco de dados de jogadores e atualiza as pontuações após o jogo.
     */
    private final JogadoresRepository<?> jogadoresRepository = new JogadoresRepositoryImpl<>();
    /**
     * Atributo do tipo Jogador que representa o vencedor da jogada.
     */
    private Jogador jogadorVencedor = DBUtils.getVencedor();
    /**
     * Atributo do tipo Jogador que representa o perdedor da jogada.
     */
    private Jogador jogadorFracasado = DBUtils.getPerdedor();

    /**
     * Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     * Faz a atualização da pontuação dos jogadores no banco de dados.
     * Caso haja algum erro ao conectar no banco de dados coloca um alerta na tela.
     * </p>
     * <p>
     * Coloca na tela o nome do jogador vencedor.
     * </p>
     * <p>
     * Se o usuário apertar no botão 'sair', muda a tela para a tela de <i>menu</i>.
     * </p>
     */
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
