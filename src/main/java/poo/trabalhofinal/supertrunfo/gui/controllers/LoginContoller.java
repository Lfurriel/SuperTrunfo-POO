package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * <h1>Classe LoginContoller</h1>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de login</i> e o programa.
 *  </p>
 *  <p>
 *  Classe responsável por logar o jogador para que ele possa jogar e seus dados serem alterados quando necessário.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class LoginContoller implements Initializable {
    /**
     * Elemento FXML que pega o nome do primeiro usuário por meio da GUI.
     */
    @FXML
    public TextField nome1;
    /**
     * Elemento FXML que pega o nome do segundo usuário por meio da GUI.
     */
    @FXML
    public TextField nome2;
    /**
     * Elemento FXML que pega a senha do primeiro usuário por meio da GUI.
     */
    @FXML
    public PasswordField senha1;
    /**
     * Elemento FXML que pega a senha do segundo usuário por meio da GUI.
     */
    @FXML
    public PasswordField senha2;
    /**
     * Elemento FXML (botão) que, ao ser clicado, permite que o primeiro usuário faça ‘login’ (se os dados estiverem corretos).
     */
    @FXML
    public Button login1;
    /**
     * Elemento FXML (botão) que, ao ser clicado, permite que o segundo usuário faça ‘login’ (se os dados estiverem corretos).
     */
    @FXML
    public Button login2;
    /**
     * Elemento FXML que coloca uma mensagem de erro na tela (para ‘login’ do usuário 1), caso haja problema para logar.
     * Mostra que o usuário conseguiu logar também.
     */
    @FXML
    public Label alerta1;
    /**
     * Elemento FXML que coloca uma mensagem de erro na tela (para ‘login’ do usuário 2), caso haja problema para logar.
     * Mostra que o usuário conseguiu logar também.
     */
    @FXML
    public Label alerta2;
    /**
     * Elemento FXML (botão) que ao ser clicado leva à tela de cadastro de usuário (fica na parte de ‘login’ de primeiro usuário).
     */
    @FXML
    public Button cadastrese1;
    /**
     * Elemento FXML (botão) que ao ser clicado leva à tela de cadastro de usuário (fica na parte de ‘login’ de segundo usuário).
     */
    @FXML
    public Button cadastrese2;
    /**
     * ('String') que representa o tipo de jogo (baralho).
     */
    private String tipo;
    /**
     * (boolean) que representa se o primeiro jogador foi logado.
     */
    private boolean jogadorLogado1 = false;
    /**
     * (boolean) que representa se o segundo jogador foi logado.
     */
    private boolean jogadorLogado2 = false;
    /**
     * (Jogador<?>) atributo que representa o primeiro jogador logado.
     */
    private Jogador<?> jogador1;
    /**
     * (Jogador<?>) atributo que representa o segundo jogador logado.
     */
    private Jogador<?> jogador2;

    /**
     * Método público que modifica o valor do atributo privado tipo, que representa o tipo do jogo.
     * @param tipo ('String') tipo do jogo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     *  Analiza o tipo do jogo e inicializa (instancia) a classe JogadoresRepositoryImpl de acordo com o tipo do jogo.
     *  Assim, por métodos dessa classe, faz conexão com o banco de dados e tenta logar os jogadores (alerta na tela se der erro).
     *  Se der certo o alerta mostra na tela que logou.
     * </p>
     * <p>
     *  Se clicar no botão de login 1, tenta logar o primeiro usuário.
     * </p>
     * <p>
     *  Se clicar em login 2, tenta logar o segundo usuário.
     * </p>
     * <p>
     * Se clicar em qualquer botão de cadastre-se leva o usuário à tela de cadastro.
     * </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login1.setOnAction(event -> loginJogadorA(event, null));
        senha1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                loginJogadorA(null, event);
            });

        login2.setOnAction(event -> loginJogadorB(event, null));
        senha2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                loginJogadorB(null, event);
        });

        cadastrese1.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));
        cadastrese2.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));
    }

    /**
     * Função responsável por logar o jogador A
     * @param event (ActionEvent) evento que será enviado para tela de jogo pelo change scene.
     * @param keyEvent (KeyEvent) evento que será enviado para tela de jogo pelo change scene.
     */
    private void loginJogadorA(ActionEvent event, KeyEvent keyEvent) {
        try {

            validaPreenchidoJogador1();
            JogadoresRepository<?> jogadoresRepository;
            if (tipo.equals("Personagem"))
                jogadoresRepository = new JogadoresRepositoryImpl<Personagem>();
            else if (tipo.equals("Gato"))
                jogadoresRepository = new JogadoresRepositoryImpl<Gato>();
            else
                jogadoresRepository = new JogadoresRepositoryImpl<LinguagensProgramacao>();
            jogador1 = jogadoresRepository.buscaJogador(nome1.getText(), senha1.getText());

            validaJogador2();

            jogadorLogado1 = true;
            login1.setDisable(true);
            nome1.setDisable(true);
            senha1.setDisable(true);
            alerta1.setText("JOGADOR 1 LOGADO");
            if (event != null)
                irParaJogo(event);
            else
                irParaJogo(keyEvent);
        } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
            alerta1.setText(e.getMessage());
        }
    }

    /**
     * Função responsável por logar o jogador B
     * @param event (ActionEvent) evento que será enviado para tela de jogo pelo change scene.
     * @param keyEvent (KeyEvent) evento que será enviado para tela de jogo pelo change scene.
     */
    private void loginJogadorB(ActionEvent event, KeyEvent keyEvent) {
        try {
            validaPreenchidoJogador2();

            JogadoresRepository<?> jogadoresRepository;
            if (tipo.equals("Personagem"))
                jogadoresRepository = new JogadoresRepositoryImpl<Personagem>();
            else if (tipo.equals("Gato"))
                jogadoresRepository = new JogadoresRepositoryImpl<Gato>();
            else
                jogadoresRepository = new JogadoresRepositoryImpl<LinguagensProgramacao>();
            jogador2 = jogadoresRepository.buscaJogador(nome2.getText(), senha2.getText());

            validaJogador1();

            jogadorLogado2 = true;
            login2.setDisable(true); // Desabilita o botão
            nome2.setDisable(true); // Desabilita os TextFields
            senha2.setDisable(true);  // Desabilita os TextFields
            alerta2.setText("JOGADOR 2 LOGADO"); // Avisaque logou

            if (event != null)
                irParaJogo(event);
            else
                irParaJogo(keyEvent);
        } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
            alerta2.setText(e.getMessage());
        }
    }

    /**
     * Método que faz validação de jogador.
     * <p>
     * Verifica se o jogador 1 já está logado e se o nome inserido para o jogador 2 é igual o do jogador 1.
     * Impede que o usuário logue e jogue contra si mesmo.
     * </p>
     * @throws InformacaoInvalidaException Se o mesmo jogador tentar logar em ambas as telas.
     */
    private void validaJogador1() throws InformacaoInvalidaException {
        if (jogadorLogado1 && nome1.getText().equals(nome2.getText()))
            throw new InformacaoInvalidaException("Você não pode jogar contra si mesmo");
    }
    /**
     * Método que faz validação de jogador.
     * <p>
     * Verifica se o jogador 2 já está logado e se o nome inserido para o jogador 1 é igual o do jogador 2.
     * Impede que o usuário logue e jogue contra si mesmo.
     * </p>
     * @throws InformacaoInvalidaException Se o mesmo jogador tentar logar em ambas as telas.
     */
    private void validaJogador2() throws InformacaoInvalidaException {
        if (jogadorLogado2 && nome2.getText().equals(nome1.getText()))
            throw new InformacaoInvalidaException("Você não pode jogar contra si mesmo");
    }

    /**
     * Método que dreciona os jogadores para o jogo.
     * Muda a tela para tela do jogo.
     * <p>
     *  Coloca uma mensagem de alerta caso haja algum erro ao tentar conectar ao jogo.
     * </p>
     * @param event (ActionEvent) evento que será enviado para tela de jogo pelo change scene.
     */
    private void irParaJogo(ActionEvent event) {
        if (jogadorLogado1 && jogadorLogado2) {
            try {
                DBUtils.iniciaJogo(jogador1, jogador2, tipo);
                DBUtils.changeScene(event, "telaJogo.fxml", "JOGO");
            } catch (SQLException | JogoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setTitle("ERRO");
                alert.showAndWait();
            }
        }
    }

    /**
     * Método que dreciona os jogadores para o jogo.
     * Muda a tela para tela do jogo.
     * <p>
     *  Coloca uma mensagem de alerta caso haja algum erro ao tentar conectar ao jogo.
     * </p>
     * @param event (KeyEvent) evento que será enviado para tela de jogo pelo change scene.
     */
    private void irParaJogo(KeyEvent event) {
        if (jogadorLogado1 && jogadorLogado2) {
            try {
                DBUtils.iniciaJogo(jogador1, jogador2, tipo);
                DBUtils.changeScene(event, "telaJogo.fxml", "JOGO");
            } catch (SQLException | JogoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setTitle("ERRO");
                alert.showAndWait();
            }
        }
    }

    /**
     * Verifica se o jogador 1 preencheu todos os campos.
     * @throws InformacaoInvalidaException Caso um dos campos não tenha sido preenchidos ao clicar em logar.
     */
    private void validaPreenchidoJogador1() throws InformacaoInvalidaException {
        if (nome1.getText().equals("") || senha1.getText().equals(""))
            throw new InformacaoInvalidaException("Jogador 1, preencha todos os campos");
    }

    /**
     * Verifica se o jogador 2 preencheu todos os campos.
     * @throws InformacaoInvalidaException Caso um dos campos não tenha sido preenchidos ao clicar em logar.
     */
    private void validaPreenchidoJogador2() throws InformacaoInvalidaException {
        if (nome2.getText().equals("") || senha2.getText().equals(""))
            throw new InformacaoInvalidaException("Jogador 2, preencha todos os campos");
    }

}
