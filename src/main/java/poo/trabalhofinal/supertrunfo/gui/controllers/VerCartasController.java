package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class VerCartasController implements Initializable {
    @FXML
    public ImageView imagem;
    @FXML
    public ImageView trunfo;
    @FXML
    public Label classificacao;
    @FXML
    public Label nome;
    @FXML
    public Label caracteristica1;
    @FXML
    public Label caracteristica2;
    @FXML
    public Label caracteristica3;
    @FXML
    public Label caracteristica4;
    @FXML
    public Label caracteristica5;
    @FXML
    public Label c1;
    @FXML
    public Label c2;
    @FXML
    public Label c3;
    @FXML
    public Label c4;
    @FXML
    public Label c5;
    @FXML
    public Label tipo;
    @FXML
    public Button sair;
    @FXML
    public Button anterior;
    @FXML
    public Button proximo;
    @FXML
    public Label alerta;

    private static int i = 0;

    private ArrayList<Carta> cartas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CartasRepository<Carta> cartasRepository = new CartasRepositoryImpl<>();
        try {
            cartas = cartasRepository.buscaTodasCartas();
            mostrarCartas(cartas.get(i));
        } catch (SQLException e) {
            alerta.setText(e.getMessage());
        }

        proximo.setOnAction(actionEvent -> {
            if (cartas != null && cartas.size() > 0 && i + 1 < cartas.size())
                mostrarCartas(cartas.get(++i));
            else if (cartas != null && i + 1 >= cartas.size())
                alerta.setText("Não tem carta posterior");
            else
                alerta.setText("Erro ao buscar cartas");
        });

        anterior.setOnAction(actionEvent -> {
            if (cartas != null && cartas.size() > 0 && i - 1 >= 0)
                mostrarCartas(cartas.get(--i));
            else if (cartas != null && i - 1 < 0)
                alerta.setText("Não tem carta anterior");
            else
                alerta.setText("Erro ao buscar cartas");
        });

        sair.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }

    private void mostrarCartas(Carta carta) {
        alerta.setText("");
        String nomeCarta = carta.getNome().toUpperCase();
        nome.setText(nomeCarta);
        classificacao.setText(String.valueOf(carta.getClassificacao()));
        try {
            imagem.setImage(new Image(carta.getImagem()));
        } catch (Exception e) {
            alerta.setText("Erro ao carregar imagem");
        }
        trunfo.setVisible(carta.isSuperTrunfo());
        if (carta instanceof Personagem) {
            tipo.setText("Personagem");
            c1.setText("Inteligência:");
            c2.setText("Força:");
            c3.setText("Coragem:");
            c4.setText("Primeira aparição:");
            c5.setText("Altura:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((Personagem) carta).getInteligencia()));
            caracteristica2.setText(String.valueOf(((Personagem) carta).getForca()));
            caracteristica3.setText(String.valueOf(((Personagem) carta).getCoragem()));
            caracteristica4.setText(String.valueOf(((Personagem) carta).getPrimeiraAparicao()));
            caracteristica5.setText(String.valueOf(((Personagem) carta).getAltura()));
        } else if (carta instanceof Gato) {
            tipo.setText("Gato");
            c1.setText("Agilidade:");
            c2.setText("Fofura:");
            c3.setText("Tempo de vida:");
            c4.setText("Agressividade:");
            c5.setText("Peso:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((Gato) carta).getAgilidade()));
            caracteristica2.setText(String.valueOf(((Gato) carta).getFofura()));
            caracteristica3.setText(String.valueOf(((Gato) carta).getTempoDeVida()));
            caracteristica4.setText(String.valueOf(((Gato) carta).getAgressividade()));
            caracteristica5.setText(String.valueOf(((Gato) carta).getPeso()));
        } else {
            tipo.setText("Linguagem de programação");
            c1.setText("Escritabilidade:");
            c2.setText("Legibilidade:");
            c3.setText("Confiabilidade:");
            c4.setText("Custo:");
            c5.setText("Salário Sênior:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((LinguagensProgramacao) carta).getEscritabilidade()));
            caracteristica2.setText(String.valueOf(((LinguagensProgramacao) carta).getLegibilidade()));
            caracteristica3.setText(String.valueOf(((LinguagensProgramacao) carta).getConfiabilidade()));
            caracteristica4.setText(String.valueOf(((LinguagensProgramacao) carta).getCusto()));
            caracteristica5.setText(String.valueOf(((LinguagensProgramacao) carta).getSalarioSenior()));
        }
    }
}
