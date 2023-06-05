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

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            }
        });
    }

    private void mostrarCartas(Carta carta) {
        nome.setText(carta.getNome());
        classificacao.setText(String.valueOf(carta.getClassificacao()));
        //TODO:: fazer o label para cada coisa (nome atributo)
        imagem.setImage(new Image(carta.getImagem()));
        trunfo.setVisible(carta.isSuperTrunfo());
        if (carta instanceof Personagem) {
            //TODO: pegar  os labels depois
        }
    }
}
