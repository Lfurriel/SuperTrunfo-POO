package poo.trabalhofinal.supertrunfo.classes.cartas;

/**
 * <h1>Classe LinguagensProgramacao</h1>
 * Subclasse que herda atributos e métodos da superclasse abstrata Carta.
 * É um tipo específico de carta do jogo. Possui atributos e métodos próprios e inerentes a cartas do baralho de LinguagensProgramacao.
 */
public class LinguagensProgramacao extends Carta {
    /**
     * 'Integer' escritabilidade: atributo privado associado à carta do tipo LinguagensProgramacao, tendo um valor de 0 a 100.
     * Representa a facilidade de escrever um código nesta linguagem.
     */
    private Integer escritabilidade;
    /**
     * 'Integer' legibilidade: atributo privado associado à carta do tipo LinguagensProgramacao, tendo um valor de 0 a 100.
     * Representa a facilidade de ler e entender um código nesta linguagem.
     */
    private Integer legibilidade;
    /**
     * 'Integer' confiabilidade: atributo privado associado à carta do tipo LinguagensProgramacao, tendo um valor de 0 a 100.
     * Representa o nível de confiabilidade de um programa desta linguagem.
     */
    private Integer confiabilidade;
    /**
     * 'Integer' custo: atributo privado associado à carta do tipo LinguagensProgramacao, tendo um valor de 0 a 100.
     * Representa o custo computacional de um programa desta linguagem.
     */
    private Integer custo;
    /**
     * 'Double' salarioSenior: atributo privado associado à carta do tipo LinguagensProgramacao, sendo um valor em reais.
     * Representa o salário que um programador sênior desta linguagem pode receber.
     */
    private Double salarioSenior;

    /**
     * Construtor vazio para a classe. Usado quando não há parâmetros para serem passados.
     * Construtor <i>default</i>.
     */
    public LinguagensProgramacao() {
    }
    /**
     * Construtor que recebe elementos a serem associados aos atributos da classe (e da superclasse) como parâmetro.
     * @param nome (‘String’) representa o nome atribuído a essa carta.
     * @param imagem (‘String’) representa a url da imagem que será colocada na carta.
     * @param superTrunfo (boolean) elemento que indica se a carta é um supertrunfo ou não.
     * @param classificacao (Classificação) indica a classificação da carta em relação a uma ordem de classificações.
     * @param escritabilidade ('Integer') indica o valor de escritabilidade associado à carta do baralho.
     * @param legibilidade ('Integer') indica o valor de legibilidade associado à carta do baralho.
     * @param confiabilidade ('Integer') indica o valor de confiabilidade associado à carta do baralho.
     * @param custo ('Integer') indica o valor de custo associado à carta do baralho.
     * @param salarioSenior ('Double') indica o valor do salário de um prgramador sênior associado à carta do baralho.
     */
    public LinguagensProgramacao(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer escritabilidade,
                                 Integer legibilidade, Integer confiabilidade, Integer custo, Double salarioSenior) {
        super(nome, imagem, superTrunfo, classificacao);
        this.escritabilidade = escritabilidade;
        this.legibilidade = legibilidade;
        this.confiabilidade = confiabilidade;
        this.custo = custo;
        this.salarioSenior = salarioSenior;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>escritabilidade</i>.
     * @return ('Integer') escritabilidade da linguagem de programação.
     */
    public Integer getEscritabilidade() {
        return escritabilidade;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>escritabilidade</i>.
     * @param escritabilidade ('Integer') novo valor de escritabilidade associado à carta.
     */
    public void setEscritabilidade(Integer escritabilidade) {
        this.escritabilidade = escritabilidade;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>legibilidade</i>.
     * @return ('Integer') legibilidade da linguagem de programação.
     */
    public Integer getLegibilidade() {
        return legibilidade;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>legibilidade</i>.
     * @param legibilidade ('Integer') novo valor de legibilidade associado à carta.
     */
    public void setLegibilidade(Integer legibilidade) {
        this.legibilidade = legibilidade;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>confiabilidade</i>.
     * @return ('Integer') confiabilidade da linguagem de programação.
     */
    public Integer getConfiabilidade() {
        return confiabilidade;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>confiabilidade</i>.
     * @param confiabilidade ('Integer') novo valor de confiabilidade associado à carta.
     */
    public void setConfiabilidade(Integer confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>custo</i>.
     * @return ('Integer') custo da linguagem de programação.
     */
    public Integer getCusto() {
        return custo;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>custo</i>.
     * @param custo ('Integer') novo valor de custo associado à carta.
     */
    public void setCusto(Integer custo) {
        this.custo = custo;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>salarioSenior</i>.
     * @return ('Double') salarioSenior da linguagem de programação (em reais).
     */
    public Double getSalarioSenior() {
        return salarioSenior;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>salarioSenior</i>.
     * @param salarioSenior ('Double') novo valor de salarioSenior associado à carta (em reais).
     */
    public void setSalarioSenior(Double salarioSenior) {
        this.salarioSenior = salarioSenior;
    }

    /**
     * Método cuja função é fazer a comparação entre a escritabilidade da carta (this) atual com a outra carta adversária.
     * A carta vencedora será a que possui o maior valor no atributo escritabilidade.
     * @param oponente (LinguagensProgramacao) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em escritabilidade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaEscritabilidade(LinguagensProgramacao oponente) {
        if (this.escritabilidade > oponente.escritabilidade)
            return 1;
        else if (this.escritabilidade < oponente.escritabilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre a legibilidade da carta (this) atual com a outra carta adversária.
     * A carta vencedora será a que possui o maior valor no atributo legibilidade.
     * @param oponente (LinguagensProgramacao) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em legibilidade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaLegibilidade(LinguagensProgramacao oponente) {
        if (this.legibilidade > oponente.legibilidade)
            return 1;
        else if (this.legibilidade < oponente.legibilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre a confiabilidade da carta (this) atual com a outra carta adversária.
     * A carta vencedora será a que possui o maior valor no atributo confiabilidade.
     * @param oponente (LinguagensProgramacao) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em confiabilidade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaConfiabilidade(LinguagensProgramacao oponente) {
        if (this.confiabilidade > oponente.confiabilidade)
            return 1;
        else if (this.confiabilidade < oponente.confiabilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre o custo da carta (this) atual com a outra carta adversária.
     * A carta vencedora será a que possui o maior valor no atributo custo.
     * @param oponente (LinguagensProgramacao) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em custo e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaCusto(LinguagensProgramacao oponente) {
        if (this.custo < oponente.custo)
            return 1;
        else if (this.custo > oponente.custo)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre o salarioSenior da carta (this) atual com a outra carta adversária.
     * A carta vencedora será a que possui o maior valor no atributo salarioSenior.
     * @param oponente (LinguagensProgramacao) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em salarioSenior e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaSalario(LinguagensProgramacao oponente) {
        if (this.salarioSenior > oponente.salarioSenior)
            return 1;
        else if (this.salarioSenior < oponente.salarioSenior)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    /**
     * Método que devolve o tipo da carta (no caso é tipo LinguagensProgramacao).
     * @return ('String') tipo da carta.
     */
    @Override
    public String toString() {
        return "Linguagem de Programação";
    }
}
