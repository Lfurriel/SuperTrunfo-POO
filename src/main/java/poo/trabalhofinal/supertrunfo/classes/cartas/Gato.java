package poo.trabalhofinal.supertrunfo.classes.cartas;

public class Gato extends Carta{
    private Integer agilidade; //0 a 100
    private Integer fofura; //0 a 100 (SEM CLUBISMO!!!)
    private Integer tempoDeVida; //em meses (se possível)
    private Integer agressividade; //0 a 100
    private Double peso; //em kg

    public Gato(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer agilidade,
                Integer fofura, Integer tempoDeVida, Integer agressividade, Double peso) {
        super(nome, imagem, superTrunfo, classificacao);
        this.agilidade = agilidade;
        this.fofura = fofura;
        this.tempoDeVida = tempoDeVida;
        this.agressividade = agressividade;
        this.peso = peso;
    }

    public Gato(){
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getFofura() {
        return fofura;
    }

    public void setFofura(Integer fofura) {
        this.fofura = fofura;
    }

    public Integer getTempoDeVida() {
        return tempoDeVida;
    }

    public void setTempoDeVida(Integer tempoDeVida) {
        this.tempoDeVida = tempoDeVida;
    }

    public Integer getAgressividade() {
        return agressividade;
    }

    public void setAgressividade(Integer agressividade) {
        this.agressividade = agressividade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int comparaAgilidade(Gato topoOponente) {
        if(this.agilidade > topoOponente.agilidade)
            return 1;
        else if (this.agilidade < topoOponente.agilidade)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    public int comparaFofura(Gato topoOponente) {
        if(this.fofura > topoOponente.fofura)
            return 1;
        else if (this.fofura < topoOponente.fofura)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    public int comparaVida(Gato topoOponente) {
        if(this.tempoDeVida > topoOponente.tempoDeVida)
            return 1;
        else if (this.tempoDeVida < topoOponente.tempoDeVida)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    public int comparaAgressividade(Gato topoOponente) {
        if(this.agressividade > topoOponente.agressividade)
            return 1;
        else if (this.agressividade < topoOponente.agressividade)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    public int comparaPeso(Gato topoOponente) {
        if(this.peso > topoOponente.peso)
            return 1;
        else if (this.peso < topoOponente.peso)
            return -1;
        else
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
    }

    @Override
    public String toString() {
        return "Gato";
    }
}
