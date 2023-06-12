package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import java.sql.SQLException;

/**
 * <h1>Interface JogoRepository<T></h1>
 * <p>
 * Interface que utiliza Generics e define o contrato (assinaturas dos métodos) que deve ser cumprido (implementação dos métodos) na classe <i>JogoRepositoryImpl</i>.
 * Utiliza Generics porque existem 3 tipos de cartas (tipos dos baralhos) que podem ser utilizados no jogo.
 * <p/>
 * @param <T> tipo genérico que vai ser preenchido por um tipo de carta para o jogo.
 */
public interface JogadoresRepository<T> {

    /**
     * Assinatura do método responsável por buscar o jogador no banco de dados.
     * @param usuario ('String') nome do usuário.
     * @param senha ('String') senha do usuário.
     * @return (Jogador<T>) o jogador procurado (T é o tipo de baralho para o jogo).
     * @throws SQLException Se não conseguir conectar com o banco de dados.
     * @throws UsuarioNaoEncontradoException Se não encontrar o usuário com os dados fornecidos.
     * @throws InformacaoInvalidaException Se as informações fornecidas forem inválidas.
     */
    Jogador<T> buscaJogador(String usuario, String senha) throws SQLException, UsuarioNaoEncontradoException, InformacaoInvalidaException;

    /**
     * Assinatura do método responsável por cadastrar um novo jogador.
     * @param novoJogador (Jogador) que será inserido (cadastrado).
     * @throws SQLException Se não conseguir conectar com o banco de dados.
     */
    void insereNovoJogador(Jogador novoJogador) throws SQLException;

    /**
     * Assinatura do método responsável por atualizar os dados dos jogadores após os jogos.
     * @param jogadorA (Jogador) um dos jogadores para atualizar os dados após a jogada.
     * @param jogadorB (Jogador) um dos jogadores para atualizar os dados após a jogada.
     * @throws SQLException Se não conseguir conectar com o banco de dados.
     */
    void updateJogadores(Jogador jogadorA, Jogador jogadorB) throws SQLException;
}