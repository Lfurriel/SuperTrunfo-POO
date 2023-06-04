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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nome1.getText().equals("") || senha1.getText().equals("")) {
                    alerta1.setText("Todos os campos devem ser preenchidos.");
                } else {

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
