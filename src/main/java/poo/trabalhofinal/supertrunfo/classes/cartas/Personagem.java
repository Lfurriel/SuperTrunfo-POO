package poo.trabalhofinal.supertrunfo.classes.cartas;


public class Personagem extends Carta {
    private Integer inteligencia; //Atributo1
    private Integer forca; //Atributo2
    private Integer coragem; //Atributo3
    private Integer primeiraAparicao; //Atributo4
    private Double altura; //Atributo5

    public Personagem () {
    }

    public Personagem(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer inteligencia,
                      Integer forca, Integer coragem, Integer primeiraAparicao, Double altura) {
        super(nome, imagem, superTrunfo, classificacao);
        this.inteligencia = inteligencia;
        this.forca = forca;
        this.coragem = coragem;
        this.primeiraAparicao = primeiraAparicao;
        this.altura = altura;
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

    public int comparaInteligencia(Personagem topoOponente) {
        if(this.inteligencia > topoOponente.inteligencia) {
            return 1;
        } else if (this.inteligencia < topoOponente.inteligencia) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    public int comparaForca(Personagem topoOponente) {
        if(this.forca > topoOponente.forca) {
            return 1;
        } else if (this.forca < topoOponente.forca) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    public int comparaCoragem(Personagem topoOponente) {
        if (this.coragem > topoOponente.coragem) {
            return 1;
        } else if (this.coragem < topoOponente.coragem) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    public int comparaAparicao(Personagem topoOponente) {
        if (this.primeiraAparicao < topoOponente.primeiraAparicao) {
            return 1;
        } else if (this.primeiraAparicao > topoOponente.primeiraAparicao) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    public int comparaAltura(Personagem topoOponente) {
        if (this.altura > topoOponente.altura) {
            return 1;
        } else if (this.altura < topoOponente.altura) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    @Override
    public String toString() {
        return "Personagem";
    }
}
