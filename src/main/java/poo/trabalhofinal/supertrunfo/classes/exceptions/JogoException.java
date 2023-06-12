package poo.trabalhofinal.supertrunfo.classes.exceptions;

/**
 * <h1>Classe de Exceção para Jogo</h1>
 * Classe que herda a superclasse Exception.
 * <p>
 *  Lança uma exceção a ser tratada caso ocorra algum erro ao iniciar o jogo.
 *  Usada para tratamento de erro.
 * </p>
 */
public class JogoException extends Exception{

    /**
     * Construtor da classe de Exceção.
     * Usa um dos construtores da superclasse (possui vários construtores com sobrecarga), passando a mensagem de erro ('String')
     * @param msg ('String') menssagem associada ao erro ocorrido.
     */
    public JogoException(String msg){
        super(msg);
    }
}
