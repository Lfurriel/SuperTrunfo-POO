package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;

import java.sql.*;
import java.util.ArrayList;

public class CartasRepositoryImpl<T> implements CartasRepository {
    @Override
    public final ArrayList<T> buscaCartas(String jogo) throws SQLException, JogoException {
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
                    personagem.setAltura(resultSet.getDouble("atributo5"));

                    cartas.add((T) personagem);
                }

            } else if (jogo.equals("Gato")) {
                while (resultSet.next()) {
                    Gato gato = new Gato();
                    gato.setNome(resultSet.getString("nome"));
                    gato.setImagem(resultSet.getString("imagem"));
                    gato.setClassificacao(resultSet.getString("classificacao"));
                    gato.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    gato.setAgilidade(resultSet.getInt("atributo1"));
                    gato.setFofura(resultSet.getInt("atributo2"));
                    gato.setTempoDeVida(resultSet.getInt("atributo3"));
                    gato.setAgressividade(resultSet.getInt("atributo4"));
                    gato.setPeso(resultSet.getDouble("atributo5"));

                    cartas.add((T) gato);
                }

            } else if (jogo.equals("LinguagensProgramacao")) {
                while (resultSet.next()) {
                    LinguagensProgramacao linguagem = new LinguagensProgramacao();
                    linguagem.setNome(resultSet.getString("nome"));
                    linguagem.setImagem(resultSet.getString("imagem"));
                    linguagem.setClassificacao(resultSet.getString("classificacao"));
                    linguagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    linguagem.setEscritabilidade(resultSet.getInt("atributo1"));
                    linguagem.setLegibilidade(resultSet.getInt("atributo2"));
                    linguagem.setConfiabilidade(resultSet.getInt("atributo3"));
                    linguagem.setCusto(resultSet.getInt("atributo4"));
                    linguagem.setSalarioSenior(resultSet.getDouble("atributo5"));

                    cartas.add((T) linguagem);
                }
            } else {
                throw new JogoException("Jogo " + jogo + " não existe!");
            }

            return cartas;
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao buscar cartas!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public final void insereNovaCarta(Personagem novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("INSERT INTO cartas VALUES (nextval('cartas_id_seq'), ?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
            // id, tipo, nome, imagem, super trunfo, inteligência, força, coragem, primeira aparição, altura, classificação
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
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao cadastrar nova carta!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public final void insereNovaCarta(Gato novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("INSERT INTO cartas VALUES (nextval('cartas_id_seq'), ?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
            // id, tipo, nome, imagem, super trunfo, agilidade, fofura, tempo de vida, agressividade, peso, classificação
            query.setString(1, "Gato");
            query.setString(2, novaCarta.getNome());
            query.setString(3, novaCarta.getImagem());
            query.setBoolean(4, novaCarta.isSuperTrunfo());
            query.setInt(5, novaCarta.getAgilidade());
            query.setInt(6, novaCarta.getFofura());
            query.setInt(7, novaCarta.getTempoDeVida());
            query.setInt(8, novaCarta.getAgressividade());
            query.setDouble(9, novaCarta.getPeso());
            query.setString(10, String.valueOf(novaCarta.getClassificacao()));

            query.executeUpdate();
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao cadastrar nova carta!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public final void insereNovaCarta(LinguagensProgramacao novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("INSERT INTO cartas VALUES (nextval('cartas_id_seq'), ?, ?, ?, ?, ?,?, ?, ?, ?, ?)");
            // id, tipo, nome, imagem, super trunfo, escritabilidade, legibilidade, confiabilidade, custo, salario senior, classificação
            query.setString(1, "LinguagensProgramacao");
            query.setString(2, novaCarta.getNome());
            query.setString(3, novaCarta.getImagem());
            query.setBoolean(4, novaCarta.isSuperTrunfo());
            query.setInt(5, novaCarta.getEscritabilidade());
            query.setInt(6, novaCarta.getLegibilidade());
            query.setInt(7, novaCarta.getConfiabilidade());
            query.setInt(8, novaCarta.getCusto());
            query.setDouble(9, novaCarta.getSalarioSenior());
            query.setString(10, String.valueOf(novaCarta.getClassificacao()));

            query.executeUpdate();
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao cadastrar nova carta!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }

    @Override
    public ArrayList<Carta> buscaTodasCartas() throws SQLException {
        ArrayList<Carta> cartas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas");
            resultSet = query.executeQuery();
            String tipo;
            while(resultSet.next()) {
                tipo = resultSet.getString("tipo");
                if(tipo.equals("Personagem")) {
                    Personagem personagem = new Personagem();
                    personagem.setNome(resultSet.getString("nome"));
                    personagem.setImagem(resultSet.getString("imagem"));
                    personagem.setClassificacao(resultSet.getString("classificacao"));
                    personagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    personagem.setInteligencia(Integer.valueOf(resultSet.getString("atributo1")));
                    personagem.setForca(Integer.valueOf(resultSet.getString("atributo2")));
                    personagem.setCoragem(Integer.valueOf(resultSet.getString("atributo3")));
                    personagem.setPrimeiraAparicao(Integer.valueOf(resultSet.getString("atributo4")));
                    personagem.setAltura(resultSet.getDouble("atributo5"));

                    cartas.add(personagem);
                } else if (tipo.equals("Gato")) {
                    Gato gato = new Gato();
                    gato.setNome(resultSet.getString("nome"));
                    gato.setImagem(resultSet.getString("imagem"));
                    gato.setClassificacao(resultSet.getString("classificacao"));
                    gato.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    gato.setAgilidade(resultSet.getInt("atributo1"));
                    gato.setFofura(resultSet.getInt("atributo2"));
                    gato.setTempoDeVida(resultSet.getInt("atributo3"));
                    gato.setAgressividade(resultSet.getInt("atributo4"));
                    gato.setPeso(resultSet.getDouble("atributo5"));

                    cartas.add(gato);
                } else {
                    LinguagensProgramacao linguagem = new LinguagensProgramacao();
                    linguagem.setNome(resultSet.getString("nome"));
                    linguagem.setImagem(resultSet.getString("imagem"));
                    linguagem.setClassificacao(resultSet.getString("classificacao"));
                    linguagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    linguagem.setEscritabilidade(resultSet.getInt("atributo1"));
                    linguagem.setLegibilidade(resultSet.getInt("atributo2"));
                    linguagem.setConfiabilidade(resultSet.getInt("atributo3"));
                    linguagem.setCusto(resultSet.getInt("atributo4"));
                    linguagem.setSalarioSenior(resultSet.getDouble("atributo5"));

                    cartas.add(linguagem);
                }
            }

            return cartas;
        } catch (SQLException e) {
            if (conexao == null)
                throw new SQLException("Erro ao conectar com banco!");
            else
                throw new SQLException("Erro ao buscar cartas!");
        } finally {
            if (conexao != null)
                conexao.close();
        }
    }
}
