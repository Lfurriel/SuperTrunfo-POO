package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
 * <h1>Classe LoginContoller<h1/>
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
    @FXML
    public TextField nome1;
    @FXML
    public TextField nome2;
    @FXML
    public PasswordField senha1;
    @FXML
    public PasswordField senha2;
    @FXML
    public Button login1;
    @FXML
    public Button login2;
    @FXML
    public Label alerta1;
    @FXML
    public Label alerta2;
    @FXML
    public Button cadastrese1;
    @FXML
    public Button cadastrese2;

    private String tipo;
    private boolean jogadorLogado1 = false;
    private boolean jogadorLogado2 = false;
    private Jogador<?> jogador1;
    private Jogador<?> jogador2;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login1.setOnAction(event -> {
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

                irParaJogo(event);
            } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
                alerta1.setText(e.getMessage());
            }

        });

        login2.setOnAction(event -> {
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
                login2.setDisable(true);
                nome2.setDisable(true);
                senha2.setDisable(true);
                alerta2.setText("JOGADOR 2 LOGADO");

                irParaJogo(event);
            } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
                alerta2.setText(e.getMessage());
            }

        });

        cadastrese1.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));
        cadastrese2.setOnAction(event -> DBUtils.changeScene(event, "cadastroUsuario.fxml", "CADASTRO"));
    }

    private void validaJogador1() throws InformacaoInvalidaException {
        if (jogadorLogado1 && nome1.getText().equals(nome2.getText()))
            throw new InformacaoInvalidaException("Você não pode jogar contra si mesmo");
    }

    private void validaJogador2() throws InformacaoInvalidaException {
        if (jogadorLogado2 && nome2.getText().equals(nome1.getText()))
            throw new InformacaoInvalidaException("Você não pode jogar contra si mesmo");
    }

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

    private void validaPreenchidoJogador1() throws InformacaoInvalidaException {
        if (nome1.getText().equals("") || senha1.getText().equals(""))
            throw new InformacaoInvalidaException("Jogador 1, preencha todos os campos");
    }

    private void validaPreenchidoJogador2() throws InformacaoInvalidaException {
        if (nome2.getText().equals("") || senha2.getText().equals(""))
            throw new InformacaoInvalidaException("Jogador 2, preencha todos os campos");
    }

}
