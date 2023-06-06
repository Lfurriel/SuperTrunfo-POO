package poo.trabalhofinal.supertrunfo.classes.cartas;

public abstract class Carta {

    private String nome;
    private String imagem;
    private boolean superTrunfo;
    private Classificacao classificacao;

    public Carta() {
    }

    public Carta(String nome, String imagem, boolean superTrunfo, Classificacao classificacao) {
        this.nome = nome;
        this.imagem = imagem;
        this.superTrunfo = superTrunfo;
        this.classificacao = classificacao;
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

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        switch (classificacao) {
            //As
            case "A1" -> this.classificacao = Classificacao.A1;
            case "A2" -> this.classificacao = Classificacao.A2;
            case "A3" -> this.classificacao = Classificacao.A3;
            case "A4" -> this.classificacao = Classificacao.A4;
            case "A5" -> this.classificacao = Classificacao.A5;
            //Bs
            case "B1" -> this.classificacao = Classificacao.B1;
            case "B2" -> this.classificacao = Classificacao.B2;
            case "B3" -> this.classificacao = Classificacao.B3;
            case "B4" -> this.classificacao = Classificacao.B4;
            case "B5" -> this.classificacao = Classificacao.B5;
            //Cs
            case "C1" -> this.classificacao = Classificacao.C1;
            case "C2" -> this.classificacao = Classificacao.C2;
            case "C3" -> this.classificacao = Classificacao.C3;
            case "C4" -> this.classificacao = Classificacao.C4;
            case "C5" -> this.classificacao = Classificacao.C5;
            //Ds
            case "D1" -> this.classificacao = Classificacao.D1;
            case "D2" -> this.classificacao = Classificacao.D2;
            case "D3" -> this.classificacao = Classificacao.D3;
            case "D4" -> this.classificacao = Classificacao.D4;
            case "D5" -> this.classificacao = Classificacao.D5;
        }
    }
}
