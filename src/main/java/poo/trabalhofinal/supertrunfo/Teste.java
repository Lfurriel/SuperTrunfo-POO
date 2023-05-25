package poo.trabalhofinal.supertrunfo;

import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;

import java.sql.*;

public class Teste {
    public static void main(String[] args) throws SQLException {
        Classificacao a = Classificacao.A1;
        Classificacao b = Classificacao.A2;
        System.out.println("a -> b: " + a.compareTo(b));

        Jogo<Personagem> partida = null;
        try {
            partida = new Jogo<>("Personagem", new Jogador<>("Lfurriel"),
                    new Jogador<>("Lfurriel"));
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }


        System.out.println(partida);
        if(partida == null)
            throw new RuntimeException("Deu bosta");

        partida.jogarPersonagem();


    }
}
