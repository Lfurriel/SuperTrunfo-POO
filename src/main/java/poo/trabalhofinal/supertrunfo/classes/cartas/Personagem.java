package poo.trabalhofinal.supertrunfo.classes.cartas;

/**
 * <h1>Classe Personagem</h1>
 * Subclasse que herda atributos e métodos da superclasse abstrata Carta.
 * É um tipo específico de carta do jogo. Possui atributos e métodos próprios e inerentes a cartas do baralho de Personagem.
 */
public class Personagem extends Carta {
    /**
     * 'Integer' inteligencia: atributo privado associado à carta do tipo Personagem, tendo valor de 0 a 100.
     */
    private Integer inteligencia;
    /**
     * 'Integer' força: atributo privado associado à carta do tipo Personagem, tendo valor de 0 a 100.
     */
    private Integer forca;
    /**
     * 'Integer' coragem: atributo privado associado à carta do tipo Personagem, tendo valor de 0 a 100.
     */
    private Integer coragem;
    /**
     * 'Integer' primeiraAparicao: atributo privado associado à carta do tipo Personagem, representando o ano em que o personagem primeiro apareceu.
     */
    private Integer primeiraAparicao;
    /**
     * 'Double' altura: atributo privado associado à carta do tipo Personagem, tendo valor em metros.
     */
    private Double altura; //Atributo5

    /**
     * Construtor vazio para a classe. Usado quando não há parâmetros para serem passados.
     * Construtor <i>default</i>.
     */
    public Personagem () {
    }

    /**
     * Construtor que recebe elementos a serem associados aos atributos da classe (e da superclasse) como parâmetro.
     * @param nome (‘String’) representa o nome atribuído a essa carta.
     * @param imagem (‘String’) representa a url da imagem que será colocada na carta.
     * @param superTrunfo (boolean) elemento que indica se a carta é um supertrunfo ou não.
     * @param classificacao (Classificação) indica a classificação da carta em relação a uma ordem de classificações.
     * @param inteligencia ('Integer') indica o valor de inteligência associado à carta do baralho.
     * @param forca ('Integer') indica o valor de força associado à carta do baralho.
     * @param coragem ('Integer') indica o valor da coragem associado à carta do baralho.
     * @param primeiraAparicao ('Integer') indica o ano da primeira aparição associado à carta do baralho.
     * @param altura ('Double') indica o valor da altura (metros) associado à carta do baralho.
     */
    public Personagem(String nome, String imagem, boolean superTrunfo, Classificacao classificacao, Integer inteligencia,
                      Integer forca, Integer coragem, Integer primeiraAparicao, Double altura) {
        super(nome, imagem, superTrunfo, classificacao);
        this.inteligencia = inteligencia;
        this.forca = forca;
        this.coragem = coragem;
        this.primeiraAparicao = primeiraAparicao;
        this.altura = altura;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>inteligencia</i>.
     * @return ('Integer') inteligencia do personagem.
     */
    public Integer getInteligencia() {
        return inteligencia;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>inteligencia</i>.
     * @param inteligencia ('Integer') novo valor de inteligência associado à carta.
     */
    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>forca</i>.
     * @return ('Integer') força do personagem.
     */
    public Integer getForca() {
        return forca;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>forca</i>.
     * @param forca ('Integer') novo valor de força associado à carta.
     */
    public void setForca(Integer forca) {
        this.forca = forca;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>coragem</i>.
     * @return ('Integer') coragem do personagem.
     */
    public Integer getCoragem() {
        return coragem;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>coragem</i>.
     * @param coragem ('Integer') novo valor de coragem associado à carta.
     */
    public void setCoragem(Integer coragem) {
        this.coragem = coragem;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>primeiraAparicao</i>.
     * @return ('Integer') primeira aparição do personagem.
     */
    public Integer getPrimeiraAparicao() {
        return primeiraAparicao;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>primeiraAparicao</i>.
     * @param primeiraAparicao ('Integer') novo valor de primeiraAparicao associado à carta.
     */
    public void setPrimeiraAparicao(Integer primeiraAparicao) {
        this.primeiraAparicao = primeiraAparicao;
    }

    /**
     * Método público que permite acessar o valor do atributo privado <i>altura</i>.
     * @return ('Double') altura (em metros) do personagem.
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * Método público que permite modificar o valor do atributo privado <i>altura</i>.
     * @param altura ('Double') novo valor de altura (em metros) associado à carta.
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * Método cuja função é fazer a comparação entre a inteligência da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo inteligência.
     * @param topoOponente (Personagem) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em inteligência e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaInteligencia(Personagem topoOponente) {
        if(this.inteligencia > topoOponente.inteligencia) {
            return 1;
        } else if (this.inteligencia < topoOponente.inteligencia) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    /**
     * Método cuja função é fazer a comparação entre a força da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo força.
     * @param topoOponente (Personagem) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em força e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaForca(Personagem topoOponente) {
        if(this.forca > topoOponente.forca) {
            return 1;
        } else if (this.forca < topoOponente.forca) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    /**
     * Método cuja função é fazer a comparação entre a coragem da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo coragem.
     * @param topoOponente (Personagem) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em coragem e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaCoragem(Personagem topoOponente) {
        if (this.coragem > topoOponente.coragem) {
            return 1;
        } else if (this.coragem < topoOponente.coragem) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    /**
     * Método cuja função é fazer a comparação entre a primeira aparição da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o menor valor no atributo primeira aparição (apareceu primeiro).
     * @param topoOponente (Personagem) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, -1 se esta (this) for maior em primeira aparição (perde) e 1 se a carta oponente for menor (ganha) que esta (this).
     */
    public int comparaAparicao(Personagem topoOponente) {
        if (this.primeiraAparicao < topoOponente.primeiraAparicao) {
            return 1;
        } else if (this.primeiraAparicao > topoOponente.primeiraAparicao) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    /**
     * Método cuja função é fazer a comparação entre a altura da carta (this) atual com a outra carta adversária (ambas no topo do baralho).
     * A carta vencedora será a que possui o maior valor no atributo altura.
     * @param topoOponente (Personagem) carta do tipo gato com a qual faremos a comparação.
     * @return (int) 0 se forem o mesmo valor, 1 se esta (this) for maior em altura e -1 se a carta oponente for maior que esta (this).
     */
    public int comparaAltura(Personagem topoOponente) {
        if (this.altura > topoOponente.altura) {
            return 1;
        } else if (this.altura < topoOponente.altura) {
            return -1;
        } else {
            return this.getClassificacao().compareTo(topoOponente.getClassificacao());
        }
    }

    /**
     * Método que devolve o tipo da carta (no caso é tipo Personagem).
     * @return ('String') tipo da carta.
     */
    @Override
    public String toString() {
        return "Personagem";
    }
}
