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
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;

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
    String tipo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nome1.getText().equals("") || senha1.getText().equals("")) {
                    alerta1.setText("Todos os campos devem ser preenchidos.");
                } else {
                    if(tipo.equals("Personagem")) {
                        JogadoresRepositoryImpl<Personagem> jogador = new JogadoresRepositoryImpl<>();
                        try {
                            jogador.buscaJogador(nome1.getText(), senha1.getText());
                        } catch (SQLException | UsuarioNaoEncontradoException e) {
                            alerta1.setText(e.getMessage());
                        }
                    } else if (tipo.equals("Gato")) {
                        JogadoresRepositoryImpl<Gato> jogador = new JogadoresRepositoryImpl<>();
                        try {
                            jogador.buscaJogador(nome1.getText(), senha1.getText());
                        } catch (SQLException | UsuarioNaoEncontradoException e) {
                            alerta1.setText(e.getMessage());
                        }
                    } else {
                        JogadoresRepositoryImpl<LinguagensProgramacao> jogador = new JogadoresRepositoryImpl<>();
                        try {
                            jogador.buscaJogador(nome1.getText(), senha1.getText());
                        } catch (SQLException | UsuarioNaoEncontradoException e) {
                            alerta1.setText(e.getMessage());
                        }
                    }

                }
            }
        });

        login2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nome2.getText().equals("") || senha2.getText().equals("")) {
                    alerta2.setText("Todos os campos devem ser preenchidos.");
                } else {

                }
            }
        });
    }


}
