package poo.trabalhofinal.supertrunfo.classes;


import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Classe Jogador <T></h1>
 * <p>
 * Classe responsável por aramazenar os dados de cada jogador e guardar dados como o baralho do  jogador que está jogando,
 * fazer o movimento do topo do baralho e pontuar a cada partida / rodada.
 * </p>
 *
 * @param <T> Generics que representa o tipo de jogo escolhido (baralho que está sendo utilizado).
 */
public class Jogador<T> {
    /**
     * 'String' nome: nome do jogador.
     */
    private String nome;
    /**
     * 'String' se4nha: senha do jogador logado.
     */
    private String senha;
    /**
     * Integer pontuacao: pontuação do jogador.
     */
    private Integer pontuacao;
    /**
     * List<T> cartas: tipo genérico que representa a lista de cartas de um tipo de baralho (escolhido para o jogo) que o jogador tem.
     */
    private List<T> cartas;

    /**
     * Construtor vazio para a classe. Usado quando não há parâmetros para serem passados.
     * Construtor <i>default<i/>.
     */
    public Jogador() {
    }

    /**
     * Construtor da classe que recebe como parâmetros elementos que serão atribuídos aos atributos
     */
    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<T> getCartas() {
        return cartas;
    }

    public void setCartas(List<T> cartas) {
        this.cartas = cartas;
    }

    public void moveTopo() {
        T topo = cartas.remove(0);
        cartas.add(topo);
    }

    public void pontua(int pontos) {
        this.pontuacao += pontos;
    }

    public void moveCartas(Jogador derrotado) {
        List<T> cartasJogadorVencedor = new ArrayList<>(this.cartas);
        List<T> cartasJogadorDerrotado = new ArrayList<>(derrotado.cartas);

        T cartaVencedora = cartasJogadorVencedor.remove(0);  // Remove a carta do topo do jogador1
        T cartaDerrotada = cartasJogadorDerrotado.remove(0);  // Remove a carta do topo do jogador2

        cartasJogadorVencedor.add(cartaDerrotada);  // Adiciona a carta derrotada no fim da lista do jogador1
        cartasJogadorVencedor.add(cartaVencedora);  // Adiciona a carta vencedora no fim da lista do jogador1

        this.cartas = cartasJogadorVencedor;  // Atualiza a lista de cartas do jogador1 com as modificações
        derrotado.cartas = cartasJogadorDerrotado;  // Atualiza a lista de cartas do jogador2 com as modificações
    }
}
