package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
import poo.trabalhofinal.supertrunfo.classes.utils.Util;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * <h1>Classe CadastroUsuarioController</h1>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de cadastro de usuário</i> e o programa.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  Permite que o usuário faça cadastro para poder jogar depois, armazenando seus dados no banco de dados de jogadores.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class CadastroUsuarioController implements Initializable {
    /**
     * Elemento FXML que pega o nome do usuário por meio da GUI.
     */
    @FXML
    public TextField nome;
    /**
     * Elemento FXML que pega a senha do usuário por meio da GUI.
     */
    @FXML
    public PasswordField senha;
    /**
     * Elemento FXML (botão) que, ao ser clicado, permite que o usuário faça cadastro.
     */
    @FXML
    public Button cadastrar;
    /**
     * Elemento FXML (botão) que, ao ser clicado, permite que o usuário volte para o menu.
     */
    @FXML
    public Button voltar;
    /**
     * Elemento FXML que coloca uma mensagem de erro na tela caso haja algum problema na hora de cadastrar.
     * Mostra que o usuário conseguiu cadastrar também.
     */
    @FXML
    public Label alerta;

    /**
     *  Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
     *  Conecta por meio da classe de JogadoresRepositoryImpl ao banco de dados de jogadores, para inserir novo jogador.
     *  Coloca uma mensagem de alerta na tela caso haja algum erro no processo.
     *  Alerta também coloca uma mensagem indicando que o jogador foi cadastrado com sucesso.
     *  Muda para a tela de menu após o cadastro.
     * </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrar.setOnAction(event -> cadastrarUsuario(event, null));

        senha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                cadastrarUsuario(null, event);

        });

        voltar.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }

    /**
     * Método para cadastrar o usuário, adicionando-no no banco de dados.
     * @param event (ActionEvent) botão clicado (cadastrar).
     * @param keyEvent (KeyEvent) quando aperta ENTER também faz o cadastro.
     */
    private void cadastrarUsuario(ActionEvent event, KeyEvent keyEvent) {
        try {
            validaPreenchidos();
            JogadoresRepository<?> jogadoresRepository = new JogadoresRepositoryImpl<>();
            jogadoresRepository.insereNovoJogador(new Jogador<>(nome.getText(), Util.codificaSenha(senha.getText())));
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("CADASTRO DE JOGADOR");
            alerta.setContentText("Jogador cadastrado com sucesso");
            alerta.showAndWait();
            if(event != null)
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            else
                DBUtils.changeScene(keyEvent, "menu.fxml", "MENU");
        } catch (InformacaoInvalidaException | SQLException e) {
            alerta.setText(e.getMessage());
        }
    }

     /**
     * Método que valida se todos os campos foram preenchidos.
     * @throws InformacaoInvalidaException Caso um dos campos não tenham sido preenchidos.
     */
    private void validaPreenchidos() throws InformacaoInvalidaException {
        if (nome.getText().equals("") || senha.getText().equals("")) {
            throw new InformacaoInvalidaException("Preencha todos os campos.");
        }
    }
}

