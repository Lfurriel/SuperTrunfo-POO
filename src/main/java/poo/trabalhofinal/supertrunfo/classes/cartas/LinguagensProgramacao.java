package poo.trabalhofinal.supertrunfo.classes.cartas;


public class LinguagensProgramacao extends Carta {
    private Integer escritabilidade;
    private Integer legibilidade;
    private Integer confiabilidade;
    private Integer custo;
    private Double salarioSenior;

    public LinguagensProgramacao() {
    }

    public LinguagensProgramacao(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer escritabilidade,
                                 Integer legibilidade, Integer confiabilidade, Integer custo, Double salarioSenior) {
        super(nome, imagem, superTrunfo, classificacao);
        this.escritabilidade = escritabilidade;
        this.legibilidade = legibilidade;
        this.confiabilidade = confiabilidade;
        this.custo = custo;
        this.salarioSenior = salarioSenior;
    }

    public Integer getEscritabilidade() {
        return escritabilidade;
    }

    public void setEscritabilidade(Integer escritabilidade) {
        this.escritabilidade = escritabilidade;
    }

    public Integer getLegibilidade() {
        return legibilidade;
    }

    public void setLegibilidade(Integer legibilidade) {
        this.legibilidade = legibilidade;
    }

    public Integer getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(Integer confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    public Integer getCusto() {
        return custo;
    }

    public void setCusto(Integer custo) {
        this.custo = custo;
    }

    public Double getSalarioSenior() {
        return salarioSenior;
    }

    public void setSalarioSenior(Double salarioSenior) {
        this.salarioSenior = salarioSenior;
    }

    public int comparaEscritabilidade(LinguagensProgramacao oponente) {
        if (this.escritabilidade > oponente.escritabilidade)
            return 1;
        else if (this.escritabilidade < oponente.escritabilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    public int comparaLegibilidade(LinguagensProgramacao oponente) {
        if (this.legibilidade > oponente.legibilidade)
            return 1;
        else if (this.legibilidade < oponente.legibilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    public int comparaConfiabilidade(LinguagensProgramacao oponente) {
        if (this.confiabilidade > oponente.confiabilidade)
            return 1;
        else if (this.confiabilidade < oponente.confiabilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    public int comparaCusto(LinguagensProgramacao oponente) {
        if (this.custo < oponente.custo)
            return 1;
        else if (this.custo > oponente.custo)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    public int comparaSalario(LinguagensProgramacao oponente) {
        if (this.salarioSenior > oponente.salarioSenior)
            return 1;
        else if (this.salarioSenior < oponente.salarioSenior)
            return -1;
        else
            return this.getClassificacao().compareTo(oponente.getClassificacao());
    }

    @Override
    public String toString() {
        return "Lingaguem de Programação";
    }
}
