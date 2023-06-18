package poo.trabalhofinal.supertrunfo.gui.controllers;

import java.awt.Desktop;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>Classe MenuController</h1>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de menu</i> e o programa.
 *  </p>
 *  <p>
 *  A tela de menu mostra as ações que o usuário pode fazer dentro do FuJu trunfo.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initialize</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class MenuController implements Initializable {
    /**
     * Elemento FXML botão que ao ser clicado leva a uma tela de opção de baralho para direcionar o jogado ao jogo.
     */
    @FXML
    public Button jogar;
    /**
     * Elemento FXML botão que ao ser clicado leva a uma tela de cadastro de novo jogador.
     */
    @FXML
    public Button novoJogador;
    /**
     * Elemento FXML botão que ao ser clicado leva a uma tela de cadastro de nova carta.
     */
    @FXML
    public Button novaCarta;
    /**
     * Elemento FXML botão que ao ser clicado leva a uma tela na qual o usuário pode ver as cartas existentes no jogo (todos os baralhos).
     */
    @FXML
    public Button verCartas;
    /**
     * Elemento FXML botão que ao ser clicado abre um link com um pdf com instruções e regras do jogo.
     */
    @FXML
    public Button regras;
    /**
     * Elemento FXML que coloca uma mensagem de alerta (erro) na tela caso algo não aconteça como esperado.
     */
    @FXML
    public Label alerta;

    /**
     * Realiza as ações conforme o botão escolhido pelo usuário.
     * <p>
     * Caso a escolha seja jogar ou cadastrar cartas, chama a tela de opção. A diferença é que o primeiro envia o campo
     * cadastro como false e o segundo como true. Assim, é possível diferenciar qual botão levou à tela de opção e realizar
     * as ações corretas.
     * </p>
     * <p>
     *  Se o usuário escolher o botão de cadastro de usuário, ele é direcionado para a tela de cadastro de jogador.
     * </p>
     * <p>
     *  Se o usuário escolher o botão de ver cartas, ele pe direcionado para tela de ver cartas.
     * </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jogar.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", false));

        novoJogador.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));

        novaCarta.setOnAction(event -> DBUtils.changeScene(event, "opcao.fxml", "OPÇÕES", true));


        verCartas.setOnAction(event -> DBUtils.changeScene(event, "verCartas.fxml", "CARTAS"));

        regras.setOnAction(event -> abrirPDF());
    }

    /**
     * Método responsável por direcionar o usuário para um link.
     * Verifica se o ambiente desktop atual suporta a classe Desktop, que faz operações de integração com a área de trabalho do ambiente.
     * Caso não suporte, manda um alerta na tela.
     * Caso seja Desktop Supported, ele tenta abrir o link da url. Se der erro mostra uma mensagem de alerta.
     */

    private void abrirPDF() {
        URL url = null;
        try {
            url = getClass().getResource("/files/telaDeExplicacao.pdf");
            if (url != null) {
                URI uri = url.toURI();

                File file = new File(uri);

                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } else {
                    alerta.setText("Não foi possível abrir o URL");
                }
            } else {
                alerta.setText("Arquivo PDF não encontrado");
            }
        } catch (URISyntaxException | IOException e) {
            alerta.setText("Não foi possível abrir o URL");
        }
    }
}
