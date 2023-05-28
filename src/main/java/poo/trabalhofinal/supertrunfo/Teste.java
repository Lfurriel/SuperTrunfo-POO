package poo.trabalhofinal.supertrunfo;

import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.JogadoresRepositoryImpl;

import java.sql.*;

public class Teste {
    public static void main(String[] args) throws SQLException {
        Classificacao a = Classificacao.A1;
        Classificacao b = Classificacao.A2;
        System.out.println("a -> b: " + a.compareTo(b));

        /* //TESTE DE UMA PARTIDA

        Jogo<Personagem> partida = null;
        try {
            partida = new Jogo<>("Personagem", "Lfurriel", "senhaA", "Julia", "senhaB");
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }


        System.out.println(partida);
        if(partida == null || partida.getJogador1() == null || partida.getJogador2() == null)
            throw new RuntimeException("Deu bosta");

        partida.jogarPersonagem();*/

        /* //TESTE DE CRIAÇÂO DE NOVO PERSONAGEM
        Personagem novoPersonagem = new Personagem();
        novoPersonagem.setNome("Teste");
        novoPersonagem.setImagem("Imagem");
        novoPersonagem.setSuperTrunfo("t");
        novoPersonagem.setClassificacao("C2");

        novoPersonagem.setInteligencia(75);
        novoPersonagem.setForca(100);
        novoPersonagem.setCoragem(23);
        novoPersonagem.setPrimeiraAparicao(2000);
        novoPersonagem.setAltura(2.4);

        CartasRepositoryImpl<Personagem> cartasRepository = new CartasRepositoryImpl<Personagem>();

        cartasRepository.insereNovaCarta(novoPersonagem);*/

        /* //TESTE NOVO JOGADOR E UPDATE
        Jogador<Personagem> jogador = new Jogador<>();
        jogador.setNome("Teste Jogador");

        JogadoresRepository<Personagem> jogadoresRepository = new JogadoresRepositoryImpl<>();
        jogadoresRepository.insereNovoJogador(jogador);

        jogador.setPontuacao(999);
        jogadoresRepository.updateJogadores(jogador, jogador);*/


    }
}
