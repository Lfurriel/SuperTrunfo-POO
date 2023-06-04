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
            if (resultSet.next()) {
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
            } else {
                alerta.setText("Não há cartas.");
            }

            ResultSet finalResultSet = resultSet;
            proximo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (finalResultSet.next()) {
                            i++;
                            nome.setText(finalResultSet.getString("nome"));
                            caminhoImagem = finalResultSet.getString("imagem");
                            Image img = new Image("file:" + caminhoImagem);
                            imagem.setImage(img);
                            classificacao.setText(finalResultSet.getString("classificacao"));
                            tipo.setText(finalResultSet.getString("super_trunfo"));
                            caracteristica1.setText(finalResultSet.getString("atributo1"));
                            caracteristica2.setText(finalResultSet.getString("atributo2"));
                            caracteristica3.setText(finalResultSet.getString("atributo3"));
                            caracteristica4.setText(finalResultSet.getString("atributo4"));
                            caracteristica5.setText(String.valueOf(finalResultSet.getDouble("atributo5")));
                        } else {
                            alerta.setText("Fim do conjunto de cartas.");
                        }
                    } catch (SQLException e) {
                        alerta.setText("Erro ao buscar a próxima carta: " + e.getMessage());
                    }
                }
            });

            ResultSet finalResultSet1 = resultSet;
            anterior.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (i > 0) {
                            if (finalResultSet1.previous()) {
                                i--;
                                nome.setText(finalResultSet1.getString("nome"));
                                caminhoImagem = finalResultSet1.getString("imagem");
                                Image img = new Image("file:" + caminhoImagem);
                                imagem.setImage(img);
                                classificacao.setText(finalResultSet1.getString("classificacao"));
                                tipo.setText(finalResultSet1.getString("super_trunfo"));
                                caracteristica1.setText(finalResultSet1.getString("atributo1"));
                                caracteristica2.setText(finalResultSet1.getString("atributo2"));
                                caracteristica3.setText(finalResultSet1.getString("atributo3"));
                                caracteristica4.setText(finalResultSet1.getString("atributo4"));
                                caracteristica5.setText(String.valueOf(finalResultSet1.getDouble("atributo5")));
                            }
                        } else {
                            alerta.setText("Primeira carta do conjunto.");
                        }
                    } catch (SQLException e) {
                        alerta.setText("Erro ao buscar a carta anterior: " + e.getMessage());
                    }
                }
            });

            sair.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DBUtils.changeScene(event, "menu.fxml", "MENU");
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
