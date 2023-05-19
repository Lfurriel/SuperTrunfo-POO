package poo.trabalhofinal.supertrunfo.classes;

import java.util.List;

public class Jogador {
    private String nome;
    private Integer pontuacao;
    private List<?> cartas;

    public Jogador(String nome, Integer pontuacao, List<?> cartas) {
        this.nome = nome;
        this.pontuacao = 0;
        this.cartas = cartas;
    }
}
