package poo.trabalhofinal.supertrunfo;

import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) throws SQLException {
        Classificacao a = Classificacao.A1;
        Classificacao b = Classificacao.A2;
        System.out.println("a -> b: " + a.compareTo(b));

        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, "Personagem");
            resultSet = query.executeQuery();

            List<Personagem> cartas = new ArrayList<>();

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

            //ResultSet insert = conexao.createStatement().executeQuery("INSERT INTO cartas values (2, 'Personagem', 'Juloia', 'imagem da juloia', true, 100, 30, 73, 2002, 1.70);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }
}
