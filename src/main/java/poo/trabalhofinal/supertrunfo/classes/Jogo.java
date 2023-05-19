package poo.trabalhofinal.supertrunfo.classes;

import javafx.scene.image.Image;
import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogo {
    ArrayList<?> baralho;
    Jogador jogador1;
    Jogador jogador2;

    /**
     * Método construtor do jogo, buscamos no banco de dados as cartas usando o primeiro parâmetro e intânciamos os dois
     * jogadores.
     * @param jogo
     * @param jogador1
     * @param jogador2
     */
    public Jogo(String jogo, String jogador1, String jogador2) throws SQLException {
        this.baralho = buscaCartasDB(jogo);
        Collections.shuffle(baralho);
        //jogador1 = new Jogador();
    }

    private ArrayList<?> buscaCartasDB(String jogo) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, jogo);
            resultSet = query.executeQuery();

            List<Personagem> cartas = new ArrayList<>();
            if(jogo.equals("Personagem")) {
                while (resultSet.next()) {
                    Personagem personagem = new Personagem();
                    personagem.setNome(resultSet.getString("nome"));
                    personagem.setImagem(resultSet.getString("imagem"));
                    personagem.setLetra(resultSet.getString("classificacao"));
                    personagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    personagem.setInteligencia(Integer.valueOf(resultSet.getString("atributo1")));
                    personagem.setForca(Integer.valueOf(resultSet.getString("atributo2")));
                    personagem.setCoragem(Integer.valueOf(resultSet.getString("atributo3")));
                    personagem.setPrimeiraAparicao(Integer.valueOf(resultSet.getString("atributo4")));
                    personagem.setAltura(Double.valueOf(resultSet.getString("atributo4")));

                    System.out.println(personagem);
                    cartas.add(personagem);
                }

                System.out.println(cartas.size());
                return (ArrayList<?>) cartas;
            } else if (jogo.equals("")) {
                //TODO:
            }
            //ResultSet insert = conexao.createStatement().executeQuery("INSERT INTO cartas values (2, 'Personagem', 'Juloia', 'imagem da juloia', true, 100, 30, 73, 2002, 1.70);");
        } catch (SQLException e) {
            throw new SQLException("Erro com banco de dados!");
        }

        return null;
    }

}
