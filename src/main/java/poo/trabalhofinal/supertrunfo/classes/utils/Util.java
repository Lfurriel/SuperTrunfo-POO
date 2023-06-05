package poo.trabalhofinal.supertrunfo.classes.utils;

import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;

public class Util {
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
