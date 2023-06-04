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
    private String caminhoImagem;

    @FXML
    public Label alerta;

    public static int i = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;
        String[] jogo = {"Personagem", "Gato", "Linguagem de Programação"};

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, Arrays.toString(jogo));
            resultSet = query.executeQuery();
            nome.setText(resultSet.getString("nome"));
            caminhoImagem = resultSet.getString("imagem");
            Image img = new Image("file:" + caminhoImagem);
            imagem.setImage(img);
            classificacao.setText(resultSet.getString("classificacao"));
            tipo.setText(resultSet.getString("super_trunfo"));
            caracteristica1.setText(resultSet.getString("atributo1"));
            caracteristica2.setText(resultSet.getString("atributo2"));
            caracteristica3.setText(resultSet.getString("atributo3"));
            caracteristica4.setText(resultSet.getString("atributo4"));
            caracteristica5.setText(String.valueOf(resultSet.getDouble("atributo5")));

//            while (resultSet.next()) {
//                nome.setText(resultSet.getString("nome"));
//                caminhoImagem = resultSet.getString("imagem");
//                Image img = new Image("file:" + caminhoImagem);
//                imagem.setImage(img);
//                classificacao.setText(resultSet.getString("classificacao"));
//                tipo.setText(resultSet.getString("super_trunfo"));
//                caracteristica1.setText(resultSet.getString("atributo1"));
//                caracteristica2.setText(resultSet.getString("atributo2"));
//                caracteristica3.setText(resultSet.getString("atributo3"));
//                caracteristica4.setText(resultSet.getString("atributo4"));
//                caracteristica5.setText(String.valueOf(resultSet.getDouble("atributo5")));
//            }
        } catch (SQLException e) {
            if (conexao == null)
                alerta.setText("Erro ao conectar com banco!");
            else
                alerta.setText("Erro ao buscar cartas!");
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    alerta.setText(e.getMessage());
                }
            }
        }
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            }
        });
    }
}
