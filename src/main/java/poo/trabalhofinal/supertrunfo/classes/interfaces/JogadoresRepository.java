package poo.trabalhofinal.supertrunfo.classes.interfaces;

import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.exceptions.UsuarioNaoEncontradoException;

import java.sql.SQLException;

public interface JogadoresRepository<T> {

    Jogador<T> buscaJogador(String usuario, String senha) throws SQLException, UsuarioNaoEncontradoException, InformacaoInvalidaException;

    void insereNovoJogador(Jogador novoJogador) throws SQLException;

    void updateJogadores(Jogador jogadorA, Jogador jogadorB) throws SQLException;
}