package poo.trabalhofinal.supertrunfo.classes.interfaces;
import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>Interface CartasRepository<T></h1>
 * <p>
 * Interface que utiliza Generics e define o contrato (assinaturas dos métodos) que deve ser cumprido na classe <i>CartasRepositoryImpl</i>.
 * Utiliza Generics porque existem 3 tipos de cartas (tipos dos baralhos) que podem ser utilizados.
 * <p/>
 * @param <T> tipo genérico que vai ser preenchido por um tipo de carta.
 */
public interface CartasRepository<T> {

    /**
     * Assinatura do método responsável por buscar as cartas para iniciar o jogo (busca o baralho).
     * @param jogo ('String') que representa o tipo de baralho que está sendo jogado.
     * @return ('ArrayList<T>') baralho de cartas que está sendo utilizado no jogo.
     * @throws SQLException Se der problema com a leitura do banco de dados.
     * @throws JogoException Se a 'String' jogo fornecida for inválida (não achar um jogo/baralho daquele tipo).
     */
    ArrayList<T> buscaCartas(String jogo) throws SQLException, JogoException;

    /**
     * Assinatura do método que faz a inserção de uma nova carta do tipo Personagem.
     * @param novaCarta (Personagem) nova carta a ser inserida no baralho.
     * @throws SQLException Se der problema ao inicializar o banco de dados.
     */
    void insereNovaCarta(Personagem novaCarta) throws SQLException;

    /**
     * Assinatura do método que faz a inserção de uma nova carta do tipo Gato.
     * @param novaCarta (Gato) nova carta a ser inserida no baralho.
     * @throws SQLException Se der problema ao inicializar o banco de dados.
     */
    void insereNovaCarta(Gato novaCarta) throws SQLException;

    /**
     * Assinatura do método que faz a inserção de uma nova carta do tipo LinguagensProgramacao.
     * @param novaCarta (LinguagensProgramacao) nova carta a ser inserida no baralho.
     * @throws SQLException Se der problema ao inicializar o banco de dados.
     */
    void insereNovaCarta(LinguagensProgramacao novaCarta) throws SQLException;

    /**
     * Assinatura do método que pega todas as cartas do jogo.
     * Devolve as listas de todos os baralhos.
     * @return ('ArrayList<T>') Lista de cartas que existem no jogo de Supertrunfo (sem ser de um tipo específico).
     * @throws SQLException Se der problema ao inicializar o banco de dados.
     */
    ArrayList<Carta> buscaTodasCartas() throws SQLException;
}