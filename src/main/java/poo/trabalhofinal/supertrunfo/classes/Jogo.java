package poo.trabalhofinal.supertrunfo.classes;

import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * <h1>Classe Jogo<T></h1>
 * <p>
 *  Classe responsável por inicializar o jogo.
 *  Esta classe representa o jogo que os usuários estão jogando.
 * </p>
 * @param <T> Generics que representa o tipo de baralho que está sendo utilizado no jogo.
 */
public class Jogo<T> {
    /**
     * ArrayList<T> baralho: lista de cartas de um tipo de baralho específico.
     */
    private ArrayList<T> baralho;
    /**
     * Jogador<T> jogadorA: primeiro jogador logado para o jogo.
     */
    private Jogador<T> jogadorA;
    /**
     * Jogador<T> jogadorB: segundo jogador logado para o jogo.
     */
    private Jogador<T> jogadorB;

    /**
     * Constructor para a classe Jogo.
     * Inicializa os jogadores da classe (this), busca o baralho no banco de dados (tipo) e inicializa a lista de cartas da classe.
     * Por meio de um método de Collections embaralha as cartas.
     * Depois divide as cartas na metade para cada usuário.
     * @param jogadorA (Jogador<T>) primeiro jogador logado para um tipo de baralho (<T>).
     * @param jogadorB (Jogador<T>) segundo jogador logado para um tipo de baralho (<T>).
     * @param tipo ('String') Irá definir o tipo do baralho.
     * @throws SQLException Se houver problema para conectar no banco de dados.
     * @throws JogoException Se houver erro para inicializar o jogo (tipo não existe).
     */
    public Jogo(Jogador<T> jogadorA, Jogador<T> jogadorB, String tipo) throws SQLException, JogoException {
        this.jogadorA = jogadorA;
        this.jogadorB = jogadorB;

        CartasRepository<T> cartasRepository = new CartasRepositoryImpl<>();

        this.baralho = cartasRepository.buscaCartas(tipo);
        Collections.shuffle(baralho);

        int tamanhoBaralho = baralho.size();
        int metade = tamanhoBaralho / 2;

        // Distribui as cartas para cada jogador
        this.jogadorA.setCartas(baralho.subList(0, metade));
        this.jogadorB.setCartas(baralho.subList(metade, tamanhoBaralho));
    }

    /**
     * Método público responsável por acessar o valor do atributo privado jogadorA.
     * @return (Jogador<T>) primeiro jogador logado.
     */
    public Jogador<T> getJogadorA() {
        return jogadorA;
    }

    /**
     * Método público responsável por acessar o valor do atributo privado jogadorB.
     * @return (Jogador<T>) segundo jogador logado.
     */
    public Jogador<T> getJogadorB() {
        return jogadorB;
    }
}
