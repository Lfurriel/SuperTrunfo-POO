package poo.trabalhofinal.supertrunfo.classes.cartas;

public abstract class Carta {

    private String nome;
    private String imagem;
    private boolean superTrunfo;
    private Classificacao letra;

    public Carta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isSuperTrunfo() {
        return superTrunfo;
    }

    public void setSuperTrunfo(String superTrunfo) {
        this.superTrunfo = superTrunfo.equals("t");
    }

    public Classificacao getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        switch (letra) {
            //As
            case "A1" -> this.letra = Classificacao.A1;
            case "A2" -> this.letra = Classificacao.A2;
            case "A3" -> this.letra = Classificacao.A3;
            case "A4" -> this.letra = Classificacao.A4;
            case "A5" -> this.letra = Classificacao.A5;
            //Bs
            case "B1" -> this.letra = Classificacao.B1;
            case "B2" -> this.letra = Classificacao.B2;
            case "B3" -> this.letra = Classificacao.B3;
            case "B4" -> this.letra = Classificacao.B4;
            case "B5" -> this.letra = Classificacao.B5;
            //Cs
            case "C1" -> this.letra = Classificacao.C1;
            case "C2" -> this.letra = Classificacao.C2;
            case "C3" -> this.letra = Classificacao.C3;
            case "C4" -> this.letra = Classificacao.C4;
            case "C5" -> this.letra = Classificacao.C5;
            //Ds
            case "D1" -> this.letra = Classificacao.D1;
            case "D2" -> this.letra = Classificacao.D2;
            case "D3" -> this.letra = Classificacao.D3;
            case "D4" -> this.letra = Classificacao.D4;
            case "D5" -> this.letra = Classificacao.D5;
        }
    }
}
