package poo.trabalhofinal.supertrunfo.classes;


import java.util.ArrayList;
import java.util.List;

public class Jogador<T> {
    private String nome;
    private String senha;
    private Integer pontuacao;
    private List<T> cartas;

    public Jogador() {
    }

    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        //TODO: criptografar a senha -> chama da util
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

    public void addBaralho(ArrayList<T> cartas) {
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
