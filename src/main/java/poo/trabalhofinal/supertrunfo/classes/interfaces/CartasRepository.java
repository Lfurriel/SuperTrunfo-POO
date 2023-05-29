package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CartasRepository<T> {

    ArrayList<T> buscaCartas(String jogo) throws SQLException, JogoException;

    void insereNovaCarta(Personagem novaCarta) throws SQLException;
    void insereNovaCarta(Gato novaCarta) throws SQLException;
    void insereNovaCarta(LinguagensProgramacao novaCarta) throws SQLException;
}