package poo.trabalhofinal.supertrunfo.classes.cartas;


public class Personagem extends Carta {
    private Integer inteligencia;
    private Integer forca;
    private Integer coragem;
    private Integer primeiraAparicao;
    private Double altura;

    public Personagem () {
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getCoragem() {
        return coragem;
    }

    public void setCoragem(Integer coragem) {
        this.coragem = coragem;
    }

    public Integer getPrimeiraAparicao() {
        return primeiraAparicao;
    }

    public void setPrimeiraAparicao(Integer primeiraAparicao) {
        this.primeiraAparicao = primeiraAparicao;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
}
