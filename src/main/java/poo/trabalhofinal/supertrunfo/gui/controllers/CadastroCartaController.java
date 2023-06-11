package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.classes.utils.Util;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
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
    public TextField imagem;
    @FXML
    public Label alerta;
    @FXML
    public Button adicionar;
    @FXML
    public Button voltar;
    String tipo;

    public void setDados(String tipo) {
        this.tipo = tipo;
        if (tipo.equals("Personagem")) {
            caracteristica1.setPromptText("Inteligência");
            caracteristica2.setPromptText("Força");
            caracteristica3.setPromptText("Coragem");
            caracteristica4.setPromptText("Primeira aparição");
            caracteristica5.setPromptText("Altura");
        } else if (tipo.equals("Gato")) {
            caracteristica1.setPromptText("Agilidade");
            caracteristica2.setPromptText("Fofura");
            caracteristica3.setPromptText("Idade");
            caracteristica4.setPromptText("Agressividade");
            caracteristica5.setPromptText("Peso (kg)");
        } else {
            caracteristica1.setPromptText("Escritabilidade");
            caracteristica2.setPromptText("Legibilidade");
            caracteristica3.setPromptText("Confiabilidade");
            caracteristica4.setPromptText("Custo");
            caracteristica5.setPromptText("Salário Sênior");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adicionar.setOnAction(event -> {
            try {
                validaPreenchidos();
                validaValores();
                if (tipo.equals("Personagem")) {
                    CartasRepository<Personagem> cartasRepository = new CartasRepositoryImpl<Personagem>();
                    cartasRepository.insereNovaCarta(new Personagem(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                            Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                            Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
                } else if (tipo.equals("Gato")) {
                    CartasRepository<Gato> cartasRepository = new CartasRepositoryImpl<Gato>();
                    cartasRepository.insereNovaCarta(new Gato(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                            Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                            Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
                } else {
                    CartasRepository<LinguagensProgramacao> cartasRepository = new CartasRepositoryImpl<LinguagensProgramacao>();
                    cartasRepository.insereNovaCarta(new LinguagensProgramacao(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                            Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                            Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
                }
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("CADASTRO DE CARTA");
                alerta.setContentText("Carta cadastrada com sucesso");
                alerta.showAndWait();
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            } catch (InformacaoInvalidaException | SQLException e) {
                if (!e.getMessage().contains("Preencha")) {
                    nome.setText("");
                    classificacao.setText("");
                    caracteristica1.setText("");
                    caracteristica2.setText("");
                    caracteristica3.setText("");
                    caracteristica4.setText("");
                    caracteristica5.setText("");
                    imagem.setText("");
                    alerta.setText(e.getMessage());
                }
            }
        });

        voltar.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }

    private void validaPreenchidos() throws InformacaoInvalidaException {
        if (nome.getText().equals("") && classificacao.getText().equals("") && caracteristica1.getText().equals("")
                && caracteristica2.getText().equals("") && caracteristica3.getText().equals("") && caracteristica4.getText().equals("")
                && caracteristica5.getText().equals(""))
            throw new InformacaoInvalidaException("Preencha todos os campos.");
    }

    private void validaValores() throws InformacaoInvalidaException {
        if(tipo.equals("Personagem")) {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 100 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 2023 || Integer.parseInt(caracteristica4.getText()) < 1000 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");

        } else if(tipo.equals("Gato")) {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 230 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 100 || Integer.parseInt(caracteristica4.getText()) < 0 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");
        } else {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 100 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 100 || Integer.parseInt(caracteristica4.getText()) < 0 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");
        }
    }
}
