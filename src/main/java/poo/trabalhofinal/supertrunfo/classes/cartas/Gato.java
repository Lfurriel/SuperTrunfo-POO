package poo.trabalhofinal.supertrunfo.classes.cartas;

/**
 * <h1>Classe Gato</h1>
 * Subclasse que herda atributos e métodos da superclasse abstrata Carta.
 * É um tipo específico de carta do jogo. Possui atributos e métodos próprios e inerentes a cartas do baralho de Gato.
 */
public class Gato extends Carta{
    /**
     * 'Integer' agilidade: atributo privado associado à carta do tipo Gato, tendo valor de 0 a 100.
     */
    private Integer agilidade;
    /**
     * 'Integer' fofura: atributo privado associado à carta do tipo Gato, tendo valor de 0 a 100.
     */
    private Integer fofura;
    /**
     * 'Integer' tempoDeVida: atributo privado associado à carta do tipo Gato, representando a idade em meses.
     */
    private Integer tempoDeVida;
    /**
     * 'Integer' agressividade: atributo privado associado à carta do tipo Gato, tendo valor de 0 a 100.
     */
    private Integer agressividade;
    /**
     * 'Double' peso: atributo privado associado à carta do tipo Gato, tendo valor em kg.
     */
    private Double peso;

    /**
     * Construtor vazio para a classe. Usado quando não há parâmetros para serem passados.
     * Construtor <i>default</i>.
     */
    public Gato(){
    }

    /**
     * Construtor que recebe elementos a serem associados aos atributos da classe (e da superclasse) como parâmetro.
     * @param nome (‘String’) representa o nome atribuído a essa carta.
     * @param imagem (‘String’) representa a url da imagem que será colocada na carta.
     * @param superTrunfo (boolean) elemento que indica se a carta é um supertrunfo ou não.
     * @param classificacao (Classificação) indica a classificação da carta em relação a uma ordem de classificações.
     * @param agilidade ('Integer') indica o valor de agilidade associado à carta do baralho.
     * @param fofura ('Integer') indica o valor de fofura associado à carta do baralho.
     * @param tempoDeVida ('Integer') indica o valor da idade (meses) associado à carta do baralho.
     * @param agressividade ('Integer') indica o valor de agressividade associado à carta do baralho.
     * @param peso ('Double') indica o valor do peso (kg) associado à carta do baralho.
     */
    public Gato(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer agilidade,
                Integer fofura, Integer tempoDeVida, Integer agressividade, Double peso) {
        super(nome, imagem, superTrunfo, classificacao);
        this.agilidade = agilidade;
        this.fofura = fofura;
        this.tempoDeVida = tempoDeVida;
        this.agressividade = agressividade;
        this.peso = peso;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>agilidade</i>.
     * @return ('Integer') agilidade do gato.
     */
    public Integer getAgilidade() {
        return agilidade;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>agilidade</i>.
     * @param agilidade ('Integer') novo valor de agilidade associado à carta.
     */
    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>fofura</i>.
     * @return ('Integer') fofura do gato.
     */
    public Integer getFofura() {
        return fofura;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>fofura</i>.
     * @param fofura ('Integer') novo valor de fofura associado à carta.
     */
    public void setFofura(Integer fofura) {
        this.fofura = fofura;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>tempoDeVida</i>.
     * @return ('Integer') idade do gato (em meses).
     */
    public Integer getTempoDeVida() {
        return tempoDeVida;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>tempoDeVida</i>.
     * @param tempoDeVida ('Integer') nova idade associada à carta (em meses).
     */
    public void setTempoDeVida(Integer tempoDeVida) {
        this.tempoDeVida = tempoDeVida;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>agressividade</i>.
     * @return ('Integer') agressividade do gato.
     */
    public Integer getAgressividade() {
        return agressividade;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>agressividade</i>.
     * @param agressividade ('Integer') novo valor de agressividade associado à carta.
     */
    public void setAgressividade(Integer agressividade) {
        this.agressividade = agressividade;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>peso</i>.
     * @return ('Double') peso do gato (em kg).
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>peso</i>.
     * @param peso ('Integer') novo peso associado à carta (em kg).
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Método cuja função é fazer a comparação entre a agilidade da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo agilidade.
     * @param topoOponente (Gato) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em agilidade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaAgilidade(Gato topoOponente) {
        if(this.agilidade > topoOponente.agilidade)
            return 1;
        else if (this.agilidade < topoOponente.agilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre a fofura da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo fofura.
     * @param topoOponente (Gato) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em fofura e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaFofura(Gato topoOponente) {
        if(this.fofura > topoOponente.fofura)
            return 1;
        else if (this.fofura < topoOponente.fofura)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre a idade da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo idade.
     * @param topoOponente (Gato) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em idade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaVida(Gato topoOponente) {
        if(this.tempoDeVida > topoOponente.tempoDeVida)
            return 1;
        else if (this.tempoDeVida < topoOponente.tempoDeVida)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre a agressividade da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo agressividade.
     * @param topoOponente (Gato) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em agressividade e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaAgressividade(Gato topoOponente) {
        if(this.agressividade > topoOponente.agressividade)
            return 1;
        else if (this.agressividade < topoOponente.agressividade)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    /**
     * Método cuja função é fazer a comparação entre o peso da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo peso.
     * @param topoOponente (Gato) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em peso e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaPeso(Gato topoOponente) {
        if(this.peso > topoOponente.peso)
            return 1;
        else if (this.peso < topoOponente.peso)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    /**
     * Método que devolve o tipo da carta (no caso é tipo Gato).
     * @return ('String') tipo da carta.
     */
    @Override
    public String toString() {
        return "Gato";
    }
}
