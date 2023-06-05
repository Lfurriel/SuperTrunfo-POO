package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private String tipo;
    private boolean jogadorLogado1 = false;
    private boolean jogadorLogado2 = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    validaPreenchidoJogador1();
                    JogadoresRepository<?> jogadoresRepository;
                    if (tipo.equals("Personagem"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Personagem>();
                    else if (tipo.equals("Gato"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Gato>();
                    else
                        jogadoresRepository = new JogadoresRepositoryImpl<LinguagensProgramacao>();
                    Jogador<?> jogador1 = jogadoresRepository.buscaJogador(nome1.getText(), senha1.getText());
                    //TODO: criar um static jogo em DBUtils e set jogados1
                    jogadorLogado1 = true;
                    irParaJogo(event);
                } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
                    alerta1.setText(e.getMessage());
                }

            }
        });

        login2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    validaPreenchidoJogador2();
                    JogadoresRepository<?> jogadoresRepository;
                    if (tipo.equals("Personagem"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Personagem>();
                    else if (tipo.equals("Gato"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Gato>();
                    else
                        jogadoresRepository = new JogadoresRepositoryImpl<LinguagensProgramacao>();
                    Jogador<?> jogador2 = jogadoresRepository.buscaJogador(nome2.getText(), senha2.getText());
                    //TODO: criar um static jogo em DBUtils e set jogados2
                    jogadorLogado2 = true;
                    irParaJogo(event);
                } catch (InformacaoInvalidaException | SQLException | UsuarioNaoEncontradoException e) {
                    alerta2.setText(e.getMessage());
                }

            }
        });
    }

    private void irParaJogo(ActionEvent event) {
        if (jogadorLogado1 && jogadorLogado2)
            DBUtils.changeScene(event, "telaJogo.fxml", "JOGO");
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
