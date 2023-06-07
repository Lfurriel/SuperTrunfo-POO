package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;

import java.sql.*;

public class JogadoresRepositoryImpl<T> implements JogadoresRepository {

    @Override
    public final Jogador<T> buscaJogador(String usuario, String senha) throws SQLException, UsuarioNaoEncontradoException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            Jogador<T> jogador = new Jogador<>();
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM jogadores WHERE usuario = ?");
            query.setString(1, usuario);
            resultSet = query.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("usuario");
                jogador.setNome(nome);
                jogador.setPontuacao(resultSet.getInt("pontuacao"));
                jogador.setSenha(resultSet.getString("senha"));

                return jogador;
            } else {
                throw new UsuarioNaoEncontradoException("Usuário " + usuario + " não encontrado!");
            }

        } catch (SQLException e) {
            if(conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao buscar jogador " + usuario);
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public final void insereNovoJogador(Jogador novoJogador) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("INSERT INTO jogadores VALUES (nextval('jogadores_id_seq'), ?, ?, ?)");
            query.setString(1, novoJogador.getNome());
            query.setInt(2, 0);
            query.setString(3, novoJogador.getSenha());
            query.executeUpdate();

        } catch (SQLException e) {
            if(conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else if (e.getMessage().contains("duplicate key"))
                throw new SQLException("Jogador já existe");
            else
                throw new SQLException("Erro ao criar jogador!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public final void updateJogadores(Jogador jogadorA, Jogador jogadorB) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("UPDATE jogadores SET pontuacao = ? WHERE usuario = ?");
            query.setInt(1, jogadorA.getPontuacao());
            query.setString(2, jogadorA.getNome());
            query.executeUpdate();

            System.out.println("Jogador A atualizado");

            query.setInt(1, jogadorB.getPontuacao());
            query.setString(2, jogadorB.getNome());
            query.executeUpdate();

            System.out.println("Jogador B atualizado");

        } catch (SQLException e) {
            if(conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao atualizar jogadores!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }
}