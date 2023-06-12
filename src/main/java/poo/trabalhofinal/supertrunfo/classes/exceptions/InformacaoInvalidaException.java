package poo.trabalhofinal.supertrunfo.classes.exceptions;

/**
 * <h1>Classe de Exceção para informações inválidas</h1>
 * Classe que herda a superclasse Exception.
 * <p>
 *  Lança uma exceção a ser tratada caso o usuário informe alguma informação incorreta ao programa.
 *  Usada para tratamento de erro.
 * </p>
 */
public class InformacaoInvalidaException extends Exception{
    /**
     * Construtor da classe de Exceção.
     * Usa um dos construtores da superclasse (possui vários construtores com sobrecarga), passando a mensagem de erro ('String')
     * @param msg ('String') menssagem associada ao erro ocorrido.
     */
    public InformacaoInvalidaException (String msg){
        super(msg);
    }
}