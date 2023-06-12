package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>Classe OpcaoController<h1/>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de opção</i> e o programa.
 *  </p>
 *  <p>
 *  A tela de opção mostra as opções de jogo/baralho disponíveis para o jogador escolher ao cadastrar cartas ou jogar.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initialize</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class OpcaoController implements Initializable {
    /**
     * Elemento FXML Button que indica que o usuário escolheu personagem.
     */
    @FXML
    public Button personagem;
    /**
     * Elemento FXML Button que indica que o usuário escolheu gatos.
     */
    @FXML
    public Button gatos;
    /**
     * Elemento FXML Button que indica que o usuário escolheu linguagens de programação.
     */
    @FXML
    public Button linguagemProg;
    /**
     * Variável booleana para indicar se essa tela foi chamada após o usuário escolher cadastrar carta ou jogar o jogo.
     */
    private boolean cadastro;

    /**
     * Método público que permite modificar o valor da variável cadastro (atributo privado).
     * @param cadastro (boolean) novo valor associado à variável cadastro.
     */
    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    /**
     * Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     *  Define os venetos que serão tratados ao usuário clicar em um dos botões.
     *  </p>
     *  <p>
     *  Chama uma nova tela e passa a escolha do usuário (personagem, gatos, linguagem de programação).
     * </p>
     * <p>
     *  Caso o cadastro seja true, chama uma tela para cadastrar nova carta, se for false, quer dizer que a tela opção apareceu
     *  ao usuário escolher a opção jogar, mudando para tela de login.
     * </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personagem.setOnAction(event -> {
            if (cadastro)
                DBUtils.changeScene(event, "cadastroCarta.fxml", "NOVA CARTA", "Personagem", true);
            else
                DBUtils.changeScene(event, "login.fxml", "LOGIN",  "Personagem", false);
        });

        gatos.setOnAction(event -> {
            if(cadastro)
                DBUtils.changeScene(event, "cadastroCarta.fxml", "NOVA CARTA", "Gato", true);
            else
                DBUtils.changeScene(event, "login.fxml", "LOGIN", "Gato", false);
        });

        linguagemProg.setOnAction(event -> {
            if(cadastro)
                DBUtils.changeScene(event, "cadastroCarta.fxml", "NOVA CARTA", "LinguagensProgramacao", true);
            else
                DBUtils.changeScene(event, "login.fxml", "LOGIN", "LinguagensProgramacao", false);
        });
    }
}
