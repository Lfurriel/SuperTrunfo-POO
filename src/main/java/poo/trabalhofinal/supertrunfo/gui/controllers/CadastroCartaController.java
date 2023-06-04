package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroCartaController implements Initializable {
    @FXML
    public TextField nome;
    @FXML
    public TextField classificacao;
    @FXML
    public TextField caracteristica1;
    @FXML
    public TextField caracteristica2;
    @FXML
    public TextField caracteristica3;
    @FXML
    public TextField caracteristica4;
    @FXML
    public TextField caracteristica5;
    @FXML
    public Label alerta;
    @FXML
    public Button adicionar;
    String tipo;

    public void setDados(String tipo) {
        if(tipo.equals("Personagem")) {
            caracteristica1.setPromptText("Inteligência");
            caracteristica2.setPromptText("Força");
            caracteristica3.setPromptText("Coragem");
            caracteristica4.setPromptText("Primeira aparição");
            caracteristica5.setPromptText("Altura");
            this.tipo = "Personagem";
        } else if (tipo.equals("Gato")) {
            caracteristica1.setPromptText("Agilidade");
            caracteristica2.setPromptText("Fofura");
            caracteristica3.setPromptText("Idade");
            caracteristica4.setPromptText("Agressividade");
            caracteristica5.setPromptText("Peso (kg)");
            this.tipo = "Gato";
        } else {
            caracteristica1.setPromptText("Escritabilidade");
            caracteristica2.setPromptText("Legibilidade");
            caracteristica3.setPromptText("Confiabilidade");
            caracteristica4.setPromptText("Custo");
            caracteristica5.setPromptText("Salário Sênior");
            this.tipo = "Linguagem de programação";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nome.getText().equals("") || classificacao.getText().equals("") || caracteristica1.getText().equals("")
                || caracteristica2.getText().equals("") || caracteristica3.getText().equals("") || caracteristica4.getText().equals("")
                || caracteristica5.getText().equals("")) {
                    nome.setText("");
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    alerta.setText("Todos os campos devem ser preenchidos.");
                } else if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText())< 0 ||
                        (Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0) ||
                        (Integer.parseInt(caracteristica3.getText()) > 100 || Integer.parseInt(caracteristica3.getText()) < 0) ||
                        (Integer.parseInt(caracteristica4.getText()) > 100 || Integer.parseInt(caracteristica4.getText()) < 0) ||
                        (Integer.parseInt(caracteristica5.getText()) > 100 || Integer.parseInt(caracteristica5.getText()) < 0)) {
                    nome.setText("");
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    alerta.setText("Valores inválidos.");
                } else if (tipo.equals("Gato") && (Double.parseDouble(caracteristica5.getText()) < 0 || Double.parseDouble(caracteristica5.getText()) > 8)) {
                    nome.setText("");
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    alerta.setText("Valores inválidos.");
                } else if (tipo.equals("Personagem") && (Double.parseDouble(caracteristica5.getText()) < 0)) {
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    alerta.setText("Valores inválidos.");
                } else if (tipo.equals("Linguagem de programação") && (Double.parseDouble(caracteristica5.getText()) < 0)) {
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    alerta.setText("Valores inválidos.");
                }
//                else {
//
//                }
            }
        });
    }
}
