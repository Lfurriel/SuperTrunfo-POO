package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;
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
    public Label alerta;
    //todo: fazer botão voltar
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    validaPreenchidos();
                    JogadoresRepository<?> jogadoresRepository = new JogadoresRepositoryImpl<>();
                    jogadoresRepository.insereNovoJogador(new Jogador<>(nome.getText(), senha.getText()));
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("CADASTRO DE JOGADOR");
                    alerta.setContentText("Jogador cadastrado com sucesso");
                    alerta.showAndWait();
                    DBUtils.changeScene(event, "menu.fxml", "MENU");
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
