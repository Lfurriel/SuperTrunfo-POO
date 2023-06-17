package poo.trabalhofinal.supertrunfo.classes.interfaces;

import org.mindrot.jbcrypt.BCrypt;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * <h1>Classe JogadoresRepositoryImpl<T></h1>
 * Classe que faz a conexão com o banco de dados de jogadores para cadastrar, logar e poder atualizar seus dados.
 * <p>
 * Implementa a interface JogadoresRepository, colocando o corpo dos métodos do contrato estabelecido com a interface.
 * </p>
 * @param <T> tipo genérico que representa o tipo de carta que foi escolhido para ser jogado pelo jogador.
 */
public class JogadoresRepositoryImpl<T> implements JogadoresRepository {

    /**
     * Acessa a lista recursos em "resource.properties"
     */
    private final ResourceBundle resources = ResourceBundle.getBundle("resources");

    /**
     * Método responsável por logar o jogador.
     * @param usuario ('String') nome do usuário.
     * @param senha ('String') senha do usuário.
     * @return (Jogador<T>) jogador encontrado no banco de dados (caso os dados fornecidos para ‘login’ sejam válidos e existam no banco de dados).
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     * @throws UsuarioNaoEncontradoException Se o usuário não for encontrado no banco de dados.
     * @throws InformacaoInvalidaException Se as informações fornecidas pelo jogador forem inválidas.
     */
    @Override
    public final Jogador<T> buscaJogador(String usuario, String senha) throws SQLException, UsuarioNaoEncontradoException, InformacaoInvalidaException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;
        try {
            Jogador<T> jogador = new Jogador<>();
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
            query = conexao.prepareStatement("SELECT * FROM jogadores WHERE usuario = ?");
            query.setString(1, usuario);
            resultSet = query.executeQuery();

            if (resultSet.next()) {
                if (BCrypt.checkpw(senha, resultSet.getString("senha"))) { // Codificação da senha → verificar se a senha está correta

                    String nome = resultSet.getString("usuario");
                    jogador.setNome(nome);
                    jogador.setPontuacao(resultSet.getInt("pontuacao"));
                    jogador.setSenha(resultSet.getString("senha"));


                    return jogador;
                } else
                    throw new InformacaoInvalidaException("Senha incorreta");
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

    /**
     * Método para cadastrar um novo jogador e inserí-lo no banco de dados.
     * Verifica se o jogador já existe (só pode ter um usuário com um tipo de dados específico).
     * @param novoJogador (Jogador) que será inserido (cadastrado).
     * @throws SQLException  Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public final void insereNovoJogador(Jogador novoJogador) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
            query = conexao.prepareStatement("INSERT INTO jogadores VALUES (nextval('jogadores_id_seq'), ?, ?, ?)");
            query.setString(1, novoJogador.getNome());
            query.setInt(2, 0); // Pontuação inicial é 0
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

    /**
     * Método responsável por atualizar a pontuação de cada jogador após o jogo.
     * @param jogadorA (Jogador) um dos jogadores para atualizar os dados após a jogada.
     * @param jogadorB (Jogador) um dos jogadores para atualizar os dados após a jogada.
     * @throws SQLException Se houver erro ao tentar conectar com o banco de dados.
     */
    @Override
    public final void updateJogadores(Jogador jogadorA, Jogador jogadorB) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;

        try {
            conexao = DriverManager.getConnection(resources.getString("jdbc-url"), resources.getString("user"), resources.getString("password"));
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