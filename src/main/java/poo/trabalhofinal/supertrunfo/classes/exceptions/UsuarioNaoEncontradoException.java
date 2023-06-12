package poo.trabalhofinal.supertrunfo.classes.exceptions;

/**
 * <h1>Classe de Exceção para Usuário não encontrado</h1>
 * Classe que herda a superclasse Exception.
 * <p>
 *  Lança uma exceção a ser tratada caso o usuário informe dados incorretos na hora de fazer login ou de cadastrar.
 *  Usada para tratamento de erro.
 * </p>
 */
public class UsuarioNaoEncontradoException extends Exception{

    /**
     * Construtor da classe de Exceção.
     * Usa um dos construtores da superclasse (possui vários construtores com sobrecarga), passando a mensagem de erro ('String')
     * @param msg ('String') menssagem associada ao erro ocorrido.
     */
    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }
}
