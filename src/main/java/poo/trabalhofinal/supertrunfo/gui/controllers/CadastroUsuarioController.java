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
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroUsuarioController implements Initializable {
    @FXML
    public TextField nome;
    @FXML
    public PasswordField senha;
    @FXML
    public Button cadastrar;
    @FXML
    public Label alerta;
    private String tipo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    validaPreenchidos();
                    JogadoresRepository<?> jogadoresRepository;
                    if (tipo.equals("Personagem"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Personagem>();
                    else if (tipo.equals("Gato"))
                        jogadoresRepository = new JogadoresRepositoryImpl<Gato>();
                    else
                        jogadoresRepository = new JogadoresRepositoryImpl<LinguagensProgramacao>();
                    jogadoresRepository.insereNovoJogador(new Jogador<>(nome.getText(), senha.getText()));
                } catch (InformacaoInvalidaException | SQLException e) {
                    alerta.setText(e.getMessage());
                }
            }
        });
    }

    private void validaPreenchidos() throws InformacaoInvalidaException {
        if (nome.getText().equals("") || senha.getText().equals(""))
            throw new InformacaoInvalidaException("Preencha todos os campos.");
    }
}
