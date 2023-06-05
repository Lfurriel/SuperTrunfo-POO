package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nome.getText().equals("") || senha.getText().equals("")) {
                    alerta.setText("Todos os campos devem ser preenchidos.");
                } else {
                    //TODO: no jogadorrepository ta o cadastro de usário, lembrando que vamos ter que adicionar a coluna 'senha' no banco
                }
            }
        });
    }
}
