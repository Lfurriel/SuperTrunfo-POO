package poo.trabalhofinal.supertrunfo.classes;

import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo<T> {
    private ArrayList<T> baralho;
    private Jogador<T> jogador1;
    private Jogador<T> jogador2;

    public Jogo(String jogo, String usuarioA, String senhaA, String usuarioB, String senhaB) throws SQLException, UsuarioNaoEncontradoException, JogoException {
        CartasRepository<T> cartasRepository = new CartasRepositoryImpl<>();
        JogadoresRepository<T> jogadoresRepository = new JogadoresRepositoryImpl<>();

        this.baralho = cartasRepository.buscaCartas(jogo);
        Collections.shuffle(baralho);


        this.jogador1 = jogadoresRepository.buscaJogador(usuarioA, senhaA);
        this.jogador2 = jogadoresRepository.buscaJogador(usuarioB, senhaB);

        int tamanhoBaralho = baralho.size();
        int metade = tamanhoBaralho / 2;

        this.jogador1.setCartas(baralho.subList(0, metade));
        this.jogador2.setCartas(baralho.subList(metade, tamanhoBaralho));
    }

    public ArrayList<T> getBaralho() {
        return baralho;
    }

    public Jogador<T> getJogador1() {
        return jogador1;
    }

    public Jogador<T> getJogador2() {
        return jogador2;
    }

    public void addBaralho(ArrayList<T> baralho) {
        this.baralho = baralho;
    }

    /**
     *
     * @return retorna o vencedor da partida
     */
    public Jogador<T> jogarPersonagem() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            Personagem topoA = (Personagem) jogador1.getCartas().get(0);
            Personagem topoB = (Personagem) jogador2.getCartas().get(0);

            int escolha = 0;
            boolean superTrunfo = false;

            if (turno % 2 == 0) { //Turno jogador 1

                if (topoA.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoA.comparaInteligencia(topoB);
                } else if (escolha == 2) {
                    compara = topoA.comparaForca(topoB);
                } else if (escolha == 3) {
                    compara = topoA.comparaCoragem(topoB);
                } else if (escolha == 4) {
                    compara = topoA.comparaAparicao(topoB);
                } else if (escolha == 5) {
                    compara = topoA.comparaAltura(topoB);
                }

                if (superTrunfo || compara == 1) { // VENCEU ROUND
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }

            } else {
                //Turno jogador 2
                if (topoB.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoB.comparaInteligencia(topoA);
                } else if (escolha == 2) {
                    compara = topoB.comparaForca(topoA);
                } else if (escolha == 3) {
                    compara = topoB.comparaCoragem(topoA);
                } else if (escolha == 4) {
                    compara = topoB.comparaAparicao(topoA);
                } else if (escolha == 5) {
                    compara = topoB.comparaAltura(topoA);
                }

                if (superTrunfo || compara == 1) { //VENCEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }
            }
            turno+=2;
            if (jogador1.getCartas().size() == 0 || jogador2.getCartas().size() == 0)
                continua = false;
        }

        return jogador1.getCartas().size() > 0 ? jogador1 : jogador2;
    }

    public Jogador<T> jogarGato() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            Gato topoA = (Gato) jogador1.getCartas().get(0);
            Gato topoB = (Gato) jogador2.getCartas().get(0);

            int escolha = 0;
            boolean superTrunfo = false;

            if (turno % 2 == 0) { //Turno jogador 1

                if (topoA.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoA.comparaAgilidade(topoB);
                } else if (escolha == 2) {
                    compara = topoA.comparaFofura(topoB);
                } else if (escolha == 3) {
                    compara = topoA.comparaVida(topoB);
                } else if (escolha == 4) {
                    compara = topoA.comparaAgressividade(topoB);
                } else if (escolha == 5) {
                    compara = topoA.comparaPeso(topoB);
                }

                if (superTrunfo || compara == 1) { // VENCEU ROUND
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }

            } else {
                //Turno jogador 2
                if (topoB.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoB.comparaAgilidade(topoA);
                } else if (escolha == 2) {
                    compara = topoB.comparaFofura(topoA);
                } else if (escolha == 3) {
                    compara = topoB.comparaVida(topoA);
                } else if (escolha == 4) {
                    compara = topoB.comparaAgressividade(topoA);
                } else if (escolha == 5) {
                    compara = topoB.comparaPeso(topoA);
                }

                if (superTrunfo || compara == 1) { //VENCEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }
            }
            turno++;
            if (jogador1.getCartas().size() == 0 || jogador2.getCartas().size() == 0)
                continua = false;
        }

        return jogador1.getCartas().size() > 0 ? jogador1 : jogador2;
    }

    public Jogador<T> jogarLinguagemProgramacao() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            LinguagensProgramacao topoA = (LinguagensProgramacao) jogador1.getCartas().get(0);
            LinguagensProgramacao topoB = (LinguagensProgramacao) jogador2.getCartas().get(0);

            int escolha = 0;
            boolean superTrunfo = false;

            if (turno % 2 == 0) { //Turno jogador 1

                if (topoA.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoA.comparaEscritabilidade(topoB);
                } else if (escolha == 2) {
                    compara = topoA.comparaLegibilidade(topoB);
                } else if (escolha == 3) {
                    compara = topoA.comparaConfiabilidade(topoB);
                } else if (escolha == 4) {
                    compara = topoA.comparaCusto(topoB);
                } else if (escolha == 5) {
                    compara = topoA.comparaSalario(topoB);
                }

                if (superTrunfo || compara == 1) { // VENCEU ROUND
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }

            } else {
                //Turno jogador 2
                if (topoB.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoB.comparaEscritabilidade(topoA);
                } else if (escolha == 2) {
                    compara = topoB.comparaLegibilidade(topoA);
                } else if (escolha == 3) {
                    compara = topoB.comparaConfiabilidade(topoA);
                } else if (escolha == 4) {
                    compara = topoB.comparaCusto(topoA);
                } else if (escolha == 5) {
                    compara = topoB.comparaSalario(topoA);
                }

                if (superTrunfo || compara == 1) { //VENCEU ROUND
                    jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }
            }
            turno+=2;
            if (jogador1.getCartas().size() == 0 || jogador2.getCartas().size() == 0)
                continua = false;
        }

        return jogador1.getCartas().size() > 0 ? jogador1 : jogador2;
    }
}
