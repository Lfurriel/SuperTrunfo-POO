package poo.trabalhofinal.supertrunfo.classes.cartas;

/**
 * <h1>Classe Carta</h1>
 * <p>
 * Classe abstrata que contém os campos gerais compartilhados por todas as classes que herdam ela.
 * Possui as características compartilhadas por todos os baralhos, independentemente do tipo.
 * </p>
 */
public abstract class Carta {
    /**
     * 'String' nome: atributo privado que indica o nome da carta.
     */
    private String nome;
    /**
     * ‘String’ url: atributo privado que indica a url da imagem da carta.
     */
    private String imagem;
    /**
     * boolean superTrunfo: atributo privado que indica se a carta é ou não super trunfo.
     */
    private boolean superTrunfo;
    /**
     * Classificação classificacao: atributo privado que indica a classificação da carta.
     */
    private Classificacao classificacao;

    /**
     * Construtor vazio para a classe. Usado quando não há parâmetros para serem passados.
     * Construtor <i>default</i>.
     */
    public Carta() {
    }

    /**
     * Construtor da superclasse abstrata, que recebe como parâmetros elementos que serão atribuídos a cada atributo.
     * @param nome (‘String’) representa o nome atribuído a essa carta.
     * @param imagem (‘String’) representa a url da imagem que será colocada na carta.
     * @param superTrunfo (boolean) elemento que indica se a carta é um supertrunfo ou não.
     * @param classificacao (Classificação) indica a classificação da carta em relação a uma ordem de classificações.
     */
    public Carta(String nome, String imagem, boolean superTrunfo, Classificacao classificacao) {
        this.nome = nome;
        this.imagem = imagem;
        this.superTrunfo = superTrunfo;
        this.classificacao = classificacao;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>nome</i>.
     * @return (‘String’) nome associado à carta.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>nome</i>.
     * @param nome (‘String’) novo nome associado à carta.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>imagem</i>.
     * @return (‘String’) url da imagem associada à carta.
     */
    public String getImagem() {
        return imagem;
    }
    /**
     * Método público que permite modificar o valor do atributo privado <i>imagem</i>.
     * @param imagem (‘String’) nova url da imagem associada à carta.
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    /**
     * Método público que permite acessar o valor do atributo privado <i>supertrunfo</i>.
     * @return (boolean) supertrunfo.
     */
    public boolean isSuperTrunfo() {
        return superTrunfo;
    }
    /**
     * Método público que permite modificar o valor do atributo privado <i>supertrunfo</i>.
     * @param superTrunfo (boolean) nova url da imagem associada à carta.
     */
    public void setSuperTrunfo(String superTrunfo) {
        this.superTrunfo = superTrunfo.equals("t");
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>classificacao</i>.
     * @return (Classificação) tipo enum que representa a classificação da carta.
     */
    public Classificacao getClassificacao() {
        return classificacao;
    }

    /**
     * Método que recebe uma 'String' representando a classificação e a transforma em um tipo enum Classificacao.
     * A análise para a transformação é feita através de um switch...case.
     * @param classificacao (‘String’) classificação da carta, para ser analisada e salvada como um tipo Classificacao.
     */
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
