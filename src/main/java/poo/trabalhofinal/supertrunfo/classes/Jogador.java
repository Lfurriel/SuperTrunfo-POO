package poo.trabalhofinal.supertrunfo.classes;

import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private Integer pontuacao;
    private List<Carta> cartas;

    public Jogador(String user) throws SQLException, UsuarioNaoEncontradoException {

        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM jogadores WHERE usuario = ?");
            query.setString(1, user);
            resultSet = query.executeQuery();
            if (resultSet.next()) {
                this.nome = resultSet.getString("usuario");
                this.pontuacao = Integer.parseInt(resultSet.getString("pontuacao"));
            } else {
                throw new UsuarioNaoEncontradoException("Usuário " + user + " não encontrado!");
            }

            //ResultSet insert = conexao.createStatement().executeQuery("INSERT INTO cartas values (2, 'Personagem', 'Juloia', 'imagem da juloia', true, 100, 30, 73, 2002, 1.70);");
        } catch (SQLException e) {
            throw new SQLException("Erro com banco de dados!");
        } finally {
            if (conexao != null)
                conexao.close();
        }

    }

    public String getNome() {
        return nome;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void addBaralho(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }


    public void addCarta(Carta topoB) {
        cartas.add(topoB);
    }

    public void moveTopo() {
        Carta topo = cartas.remove(0);
        cartas.add(topo);
    }

    public void pontua(int pontos) {
        this.pontuacao += pontos;
    }

    public void removeTopo() {
        cartas.remove(0);
    }
}
