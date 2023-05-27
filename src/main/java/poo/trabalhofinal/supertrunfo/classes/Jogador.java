package poo.trabalhofinal.supertrunfo.classes;


import java.util.ArrayList;
import java.util.List;

public class Jogador<T> {
    private String nome;
    private Integer pontuacao;
    private List<T> cartas;

    public Jogador() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        List<T> cartasJogador1 = new ArrayList<>(this.cartas);
        List<T> cartasJogador2 = new ArrayList<>(derrotado.cartas);

        T cartaVencedora = cartasJogador1.remove(0);  // Remove a carta do topo do jogador1
        T cartaDerrotada = cartasJogador2.remove(0);  // Remove a carta do topo do jogador2

        cartasJogador1.add(cartaVencedora);  // Adiciona a carta vencedora no fim da lista do jogador1
        cartasJogador1.add(cartaDerrotada);  // Adiciona a carta derrotada no fim da lista do jogador1

        this.cartas = cartasJogador1;  // Atualiza a lista de cartas do jogador1 com as modificações
        derrotado.cartas = cartasJogador2;  // Atualiza a lista de cartas do jogador2 com as modificações
    }
}
