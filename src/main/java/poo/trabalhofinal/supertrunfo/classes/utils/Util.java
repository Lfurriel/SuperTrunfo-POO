package poo.trabalhofinal.supertrunfo.classes.utils;

import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import org.mindrot.jbcrypt.BCrypt;

/**
 * <h1>Classe Util</h1>
 * <p>
 *  Classe com funcionalidades/métodos utilitários para diferentes contextos (terefas).
 *  Fornece métodos estáticos que podem ser acessados sem a necessidade de crinstanciar a classe.
 * </p>
 */
public class Util {
    /**
     * Método responsável por codificar a senha.
     * Utiliza a biblioteca BCrypt para criptografar a senha.
     * @param senha ('String') senha que o usuário digitou.
     * @return senha codificada.
     */
    public static String codificaSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    /**
     * Método para transformar uma 'String' que representa uma classificação em um tipo Classificacao(enum).
     * @param str ('String') que representa a classificação.
     * @return (Classificação) classificação convertida no tipo enum.
     */
    public static Classificacao stringToClassificacao(String str) {
        switch (str) {
            //As
            case "A1" -> {
                return Classificacao.A1;
            }
            case "A2" -> {
                return Classificacao.A2;
            }
            case "A3" -> {
                return Classificacao.A3;
            }
            case "A4" -> {
                return Classificacao.A4;
            }
            case "A5" -> {
                return Classificacao.A5;
            }
            //Bs
            case "B1" -> {
                return Classificacao.B1;
            }
            case "B2" -> {
                return Classificacao.B2;
            }
            case "B3" -> {
                return Classificacao.B3;
            }
            case "B4" -> {
                return Classificacao.B4;
            }
            case "B5" -> {
                return Classificacao.B5;
            }
            //Cs
            case "C1" -> {
                return Classificacao.C1;
            }
            case "C2" -> {
                return Classificacao.C2;
            }
            case "C3" -> {
                return Classificacao.C3;
            }
            case "C4" -> {
                return Classificacao.C4;
            }
            case "C5" -> {
                return Classificacao.C5;
            }
            //Ds
            case "D1" -> {
                return Classificacao.D1;
            }
            case "D2" -> {
                return Classificacao.D2;
            }
            case "D3" -> {
                return Classificacao.D3;
            }
            case "D4" -> {
                return Classificacao.D4;
            }
            case "D5" -> {
                return Classificacao.D5;
            }
        }
        return Classificacao.B2;
    }
}
