package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;

import java.sql.*;
import java.util.ArrayList;

public class CartasRepositoryImpl<T> implements CartasRepository {
    @Override
    public ArrayList<T> buscaCartas(String jogo) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, jogo);
            resultSet = query.executeQuery();

            ArrayList<T> cartas = new ArrayList<>();
            if (jogo.equals("Personagem")) {
                while (resultSet.next()) {
                    Personagem personagem = new Personagem();
                    personagem.setNome(resultSet.getString("nome"));
                    personagem.setImagem(resultSet.getString("imagem"));
                    personagem.setClassificacao(resultSet.getString("classificacao"));
                    personagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    personagem.setInteligencia(Integer.valueOf(resultSet.getString("atributo1")));
                    personagem.setForca(Integer.valueOf(resultSet.getString("atributo2")));
                    personagem.setCoragem(Integer.valueOf(resultSet.getString("atributo3")));
                    personagem.setPrimeiraAparicao(Integer.valueOf(resultSet.getString("atributo4")));
                    personagem.setAltura(Double.valueOf(resultSet.getString("atributo4")));

                    System.out.println(personagem);
                    cartas.add((T) personagem);
                }

                System.out.println(cartas.size());
                return cartas;
            } else if (jogo.equals("")) {
                //TODO:
            }
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro com banco de dados!");
            else
                throw new SQLException("Erro ao buscar cartas!");
        } finally {
            if (conexao != null)
                conexao.close();
        }

        return null; //TODO: Return null por enquanto que não tem os demais baralhos
    }

    @Override
    public void insereNovaCarta(Personagem novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("INSERT INTO cartas VALUES (nextval('cartas_id_seq'), ?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
            query.setString(1, "Personagem");
            query.setString(2, novaCarta.getNome());
            query.setString(3, novaCarta.getImagem());
            query.setBoolean(4, novaCarta.isSuperTrunfo());
            query.setInt(5, novaCarta.getInteligencia());
            query.setInt(6, novaCarta.getForca());
            query.setInt(7, novaCarta.getCoragem());
            query.setInt(8, novaCarta.getPrimeiraAparicao());
            query.setDouble(9, novaCarta.getAltura());
            query.setString(10, String.valueOf(novaCarta.getClassificacao()));

            query.executeUpdate();
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro com banco de dados!");
            else
                throw new SQLException("Erro ao cadastrar nova carta!");


        }
    }
}
