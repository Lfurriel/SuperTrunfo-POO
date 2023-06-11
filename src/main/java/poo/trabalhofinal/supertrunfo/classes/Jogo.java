package poo.trabalhofinal.supertrunfo.classes;

import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
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
    private Jogador<T> jogadorA;
    private Jogador<T> jogadorB;

    public Jogo(Jogador<T> jogadorA, Jogador<T> jogadorB, String tipo) throws SQLException, JogoException {
        this.jogadorA = jogadorA;
        this.jogadorB = jogadorB;

        CartasRepository<T> cartasRepository = new CartasRepositoryImpl<>();

        this.baralho = cartasRepository.buscaCartas(tipo);
        Collections.shuffle(baralho);

        int tamanhoBaralho = baralho.size();
        int metade = tamanhoBaralho / 2;

        this.jogadorA.setCartas(baralho.subList(0, metade));
        this.jogadorB.setCartas(baralho.subList(metade, tamanhoBaralho));
    }

    public Jogo(String jogo, String usuarioA, String senhaA, String usuarioB, String senhaB) throws SQLException, UsuarioNaoEncontradoException, JogoException, InformacaoInvalidaException {
        CartasRepository<T> cartasRepository = new CartasRepositoryImpl<>();
        JogadoresRepository<T> jogadoresRepository = new JogadoresRepositoryImpl<>();

        this.baralho = cartasRepository.buscaCartas(jogo);
        Collections.shuffle(baralho);


        this.jogadorA = jogadoresRepository.buscaJogador(usuarioA, senhaA);
        this.jogadorB = jogadoresRepository.buscaJogador(usuarioB, senhaB);


        int tamanhoBaralho = baralho.size();
        int metade = tamanhoBaralho / 2;

        this.jogadorA.setCartas(baralho.subList(0, metade));
        this.jogadorB.setCartas(baralho.subList(metade, tamanhoBaralho));
    }

    public ArrayList<T> getBaralho() {
        return baralho;
    }

    public Jogador<T> getJogadorA() {
        return jogadorA;
    }

    public Jogador<T> getJogadorB() {
        return jogadorB;
    }

    public void addBaralho(ArrayList<T> baralho) {
        this.baralho = baralho;
    }

    /**
     * @return retorna o vencedor da partida
     */
    public Jogador<T> jogarPersonagemTerminal() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            Personagem topoA = (Personagem) jogadorA.getCartas().get(0);
            Personagem topoB = (Personagem) jogadorB.getCartas().get(0);

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
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
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
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
                }
            }
            turno += 2;
            if (jogadorA.getCartas().size() == 0 || jogadorB.getCartas().size() == 0)
                continua = false;
        }

        return jogadorA.getCartas().size() > 0 ? jogadorA : jogadorB;
    }

    public Jogador<T> jogarTerminalGato() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            Gato topoA = (Gato) jogadorA.getCartas().get(0);
            Gato topoB = (Gato) jogadorB.getCartas().get(0);

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
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
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
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
                }
            }
            turno++;
            if (jogadorA.getCartas().size() == 0 || jogadorB.getCartas().size() == 0)
                continua = false;
        }

        return jogadorA.getCartas().size() > 0 ? jogadorA : jogadorB;
    }

    public Jogador<T> jogarTerminalLinguagemProgramacao() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            LinguagensProgramacao topoA = (LinguagensProgramacao) jogadorA.getCartas().get(0);
            LinguagensProgramacao topoB = (LinguagensProgramacao) jogadorB.getCartas().get(0);

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
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
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
                    jogadorB.moveCartas(jogadorA);

                    jogadorB.pontua(10);
                    jogadorA.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    jogadorA.moveCartas(jogadorB);

                    jogadorA.pontua(10);
                    jogadorB.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogadorA.moveTopo();
                    jogadorB.moveTopo();
                }
            }
            turno += 2;
            if (jogadorA.getCartas().size() == 0 || jogadorB.getCartas().size() == 0)
                continua = false;
        }

        return jogadorA.getCartas().size() > 0 ? jogadorA : jogadorB;
    }
}
