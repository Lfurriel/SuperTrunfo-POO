package poo.trabalhofinal.supertrunfo.classes;


import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Classe Jogador</h1>
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
     * Construtor <i>default</i>.
     */
    public Jogador() {
    }

    /**
     * Construtor da classe que recebe como parâmetros elementos que serão atribuídos aos atributos.
     * @param nome ('String'): nome do jogador.
     * @param senha ('String'): senha do jogador.
     */
    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Método público que permite acessar o valor do atributo privado nome do Jogador.
     * @return ('String') nome do jogador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método público que permite modificar o atributo privado nome do Jogador.
     * @param nome ('String'): novo nome assumido pelo jogador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método público que permite acessar o valor do atributo privado senha do Jogador.
     * @return ('String') senha do jogador.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Método público que permite modificar o atributo privado senha do Jogador.
     * @param senha ('String'): nova senha assumido pelo jogador.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Método público que permite acessar o valor do atributo privado pontuação.
     * @return (Integer) pontuação do jogador.
     */
    public Integer getPontuacao() {
        return pontuacao;
    }

    /**
     * Método público que permite modificar o valor do campo privado pontuação do jogador.
     * @param pontuacao (Integer) nova pontuação.
     */
    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Método público que permite acessar a lista de cartas disponível para o jogador (atributo privado).
     * @return (List<T>) baralho do jogador.
     */
    public List<T> getCartas() {
        return cartas;
    }

    /**
     * Método público que permite modificar o atributo privado baralho do jogador.
     * @param cartas (List<T>) nova lista de cartas do jogador.
     */
    public void setCartas(List<T> cartas) {
        this.cartas = cartas;
    }

    /**
     * Método público que tira a carta do topo do baralho e a adiciona no final do baralho.
     * Usado após uma partida.
     */
    public void moveTopo() {
        T topo = cartas.remove(0);
        cartas.add(topo);
    }

    /**
     * Método que atualiza a quantidade de pontos do jogador.
     * @param pontos (int) pontos da partida (positivo se vencer e negativo se perder).
     */
    public void pontua(int pontos) {
        this.pontuacao += pontos;
    }

    /**
     * Método responsãvel por atualizar o baralho de cartas dos dois jogadores após uma partida.
     * <p>
     * Listas auxiliares são craiadas para armazenar ambos os baralhos (de ambos os jogadores).
     * O topo do vencedor é removido e adicionado ao final do seu baralho.
     * O topo do perdedor é removido e adicionado ao final do baralho do vencedor.
     * Faz a atualização dos baralhos.
     * </p>
     * @param derrotado (Jogador) jogador que perdeu a partida em relação ao jogador desta instância (this).
     */
    public void moveCartas(Jogador<T> derrotado) {
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
