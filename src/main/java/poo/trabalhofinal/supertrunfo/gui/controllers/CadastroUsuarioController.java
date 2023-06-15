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

public class CadastroUsuarioController implements Initializable {
    @FXML
    public TextField nome;
    @FXML
    public PasswordField senha;
    @FXML
    public Button cadastrar;
    @FXML
    public Button voltar;
    @FXML
    public Label alerta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrar.setOnAction(event -> cadastrarUsuario(event, null));

        senha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                cadastrarUsuario(null, event);

        });

        voltar.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }

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

    private void validaPreenchidos() throws InformacaoInvalidaException {
        if (nome.getText().equals("") || senha.getText().equals("")) {
            throw new InformacaoInvalidaException("Preencha todos os campos.");
        }
    }
}

