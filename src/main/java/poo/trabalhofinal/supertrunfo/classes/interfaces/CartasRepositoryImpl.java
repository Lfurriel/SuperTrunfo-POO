package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <h1>Classe CartasRepositoryImpl</h1>
 * Classe que faz a conexão com o banco de dados de cartas para buscar cartas, ver cartas e inserir cartas ou modificar o banco de dados.
 * <p>
 * Implementa a interface CartasRepository, colocando o corpo dos métodos do contrato estabelecido com a interface.
 * </p>
 * @param <T> tipo genérico que representa o tipo de cartas que serão utilizadas.
 */
public class CartasRepositoryImpl<T> implements CartasRepository<T> {

    /**
     * Acessa a lista recursos em "resource.properties"
     */
    private final ResourceBundle resources = ResourceBundle.getBundle("resources");

    /**
     * Busca o baralho de cartas de um tipo ('String') jogo no banco de dados, para as cartas poderem ser utilizadas no jogo.
     * @param jogo ('String') que representa o tipo de baralho que está sendo jogado.
     * @return (ArrayList<T>) lista que representa o baralho de cartas de um tipo.
     * @throws SQLException Se houver falha na conexão com o banco de dados.
     * @throws JogoException Se a 'String' jogo fornecida for inválida (não achar um jogo/baralho daquele tipo).
     */
    @Override
    public final ArrayList<T> buscaCartas(String jogo) throws SQLException, JogoException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, jogo);
            resultSet = query.executeQuery();

            ArrayList<T> cartas = new ArrayList<>();
            switch (jogo) {
                case "Personagem" -> {
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
                }
                case "Gato" -> {
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
                }
                case "LinguagensProgramacao" -> {
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
                }
                default -> throw new JogoException("Jogo " + jogo + " não existe!");
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

    /**
     * Método para inserir uma nova carta no baralho de Personagem (inserir no banco de dados).
     * @param novaCarta (Personagem) nova carta a ser inserida no baralho.
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public final void insereNovaCarta(Personagem novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
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

    /**
     * Método sobrecarregado para inserir uma nova carta no baralho de Gato (inserir no banco de dados).
     * @param novaCarta (Gato) nova carta a ser inserida no baralho.
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public final void insereNovaCarta(Gato novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
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

    /**
     * Método sobrecarregado para inserir uma nova carta no baralho de LinguagensProgramacao (inserir no banco de dados).
     * @param novaCarta (LinguagensProgramacao) nova carta a ser inserida no baralho.
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public final void insereNovaCarta(LinguagensProgramacao novaCarta) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
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

    /**
     * Método que acessa o banco de dados para ver todas as cartas do jogo (todos os baralhos).
     * Na GUI há uma tela que permite ver todas as cartas.
     * A ordenação das cartas é feita po <i>id</i>, contador incrementado ao inserir a carta no banco de dados.
     * @return (ArrayList<Carta>) lista de todas as cartas do jogo.
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public ArrayList<Carta> buscaTodasCartas() throws SQLException {
        ArrayList<Carta> cartas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
            query = conexao.prepareStatement("SELECT * FROM cartas ORDER BY tipo ASC");
            resultSet = query.executeQuery();
            String tipo;
            while(resultSet.next()) {
                tipo = resultSet.getString("tipo");
                if(tipo.equals("Personagem")) {
                    Personagem personagem = new Personagem();
                    personagem.setNome(resultSet.getString("nome").toUpperCase());
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
                    gato.setNome(resultSet.getString("nome").toUpperCase());
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
                    linguagem.setNome(resultSet.getString("nome").toUpperCase());
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
