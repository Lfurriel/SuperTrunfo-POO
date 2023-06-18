package poo.trabalhofinal.supertrunfo.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poo.trabalhofinal.supertrunfo.HelloApplication;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.gui.controllers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * <h1>Classe DBUtils</h1>
 * <p>
 *  Classe responsável por mudar as telas da aplicação GUI.
 * </p>
 */
public class DBUtils {
    /**
     * (Image) ícone presente nas telas.
     */
    private static Image icon = new Image(HelloApplication.class.getResource("/poo/trabalhofinal/supertrunfo/gui/fujutrunfo.png").toExternalForm());
    /**
     * (Jogo) atributo do tipo Jogo que é passado de parâmetro em algumas mudannças de cena.
     */
    private static Jogo jogo;
    /**
     * (‘String’) representa o tipo do jogo, passado como parâmetro em algumas mudanças de cena.
     */
    private static String tipoJogo;
    /**
     * (Jogador) representa o jogador vencedor e é usado como parâmetro em algumas mudanças de cena.
     */
    private static Jogador vencedor;
    /**
     * (Jogador) representa o jogador perdedor e é usado como parâmetro em algumas mudanças de cena.
     */
    private static Jogador perdedor;
    /**
     * (boolean) que indica se houve algum empate.
     */
    private static boolean empatou;

    /**
     * Método para definir o fim da partida e definir o estado atual do jogo.
     * @param a (Jogador) vencedor.
     * @param b (Jogador) perdedor.
     * @param empate (boolean) indica empate.
     */
    public static void setFimPartida(Jogador a, Jogador b, boolean empate){
        vencedor = a;
        perdedor = b;
        empatou = empate;
    }

    /**
     * Método para acessar o valor do atributo jogador vencedor.
     * @return (Jogador) retorna o vencedor.
     */
    public static Jogador getVencedor() {
        return vencedor;
    }

    /**
     * Método para acessar o valor do atributo jogador vencedor.
     * @return (Jogador) retorna o perdedor.
     */
    public static Jogador getPerdedor() {
        return perdedor;
    }

    /**
     * Método para acessar o atributo de empate.
     * @return (boolean) true se houve empate ou false se não.
     */
    public static boolean getEmpate() {
        return empatou;
    }

    /**
     * Métrodo para inicializar o jogo.
     * @param jogadorA (Jogador) primeiro jogador a jogar.
     * @param jogadorB (Jogador) segundo jogador a jogar.
     * @param tipo (String) tipo do jogo.
     * @throws SQLException Se houver problema ao conectar com banco de dados.
     * @throws JogoException Se houver problema ao inicializar o jogo.
     */
    public static void iniciaJogo(Jogador<?> jogadorA, Jogador<?> jogadorB, String tipo) throws SQLException, JogoException {
        jogo = new Jogo(jogadorA, jogadorB, tipo);
        tipoJogo = tipo;
    }

    /**
     * Método para acessar o atributo de jogo.
     * @return (Jogo) true se houve empate ou false se não.
     */
    public static Jogo getJogo() {
        return jogo;
    }

    /**
     * Método para acessar o atributo de tipo de jogo.
     * @return (String) tipo do jogo.
     */
    public static String getTipoJogo() {
        return tipoJogo;
    }

    /**
     * Método de mudança de cena (mais simples).
     * <p>
     *  É declarada uma variável root (Parent) e inicializada como null. Essa variável será usada para armazenar
     *  o conteúdo raiz da nova cena.
     * </p>
     * <p>
     *  O bloco try-catch captura qualquer exceção que pode acontecer ao abrir o FXML.
     * </p>
     * @param event (ActionEvent) Botão pressionado.
     * @param fxmlFile (‘String’) Nome do arquivo fxml.
     * @param title ('String') Titulo da cena.
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile),
                    ResourceBundle.getBundle("resources"));

            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        createStage(title, root, event);
    }

    /**
     * Método de mudança de cena.
     * <p>
     *  É declarada uma variável root (Parent) e inicializada como null. Essa variável será usada para armazenar
     *  o conteúdo raiz da nova cena.
     * </p>
     * <p>
     *  O bloco try-catch captura qualquer exceção que pode acontecer ao abrir o FXML.
     * </p>
     * <p>
     *  A diferença do outro método, é que trata de um evento de apertar uma tecla do teclado.
     * </p>
     * @param event (KeyEvent) tecla pressionada.
     * @param fxmlFile (‘String’) Nome do arquivo fxml.
     * @param title ('String') Titulo da cena.
     */
    public static void changeScene(KeyEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile),
                    ResourceBundle.getBundle("resources"));

            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        createStage(title, root, event);
    }

    /**
     * Método de mudança de cena.
     * <p>
     *  É declarada uma variável root (Parent) e inicializada como null. Essa variável será usada para armazenar
     *  o conteúdo raiz da nova cena.
     * </p>
     * <p>
     *  O bloco try-catch captura qualquer exceção que pode acontecer ao abrir o FXML.
     * </p>
     * <p>
     *  Recebe mais uma String que representa o tipo do jogo escolhido na tela anterior e um boolean que indica se a tela
     *  que será aberta agora será a de cadastro de cartas ou a tela de login para jogar.
     * </p>
     * @param event (ActionEvent) Botão pressionado.
     * @param fxmlFile (‘String’) Nome do arquivo fxml.
     * @param title ('String') Titulo da cena.
     * @param tipo ('String') Tipo do jogo.
     * @param cadastro (boolean) true se for para cadastrar cartas e false se for para login.
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String tipo, boolean cadastro) {

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile),
                    ResourceBundle.getBundle("resources"));

            root = loader.load();
            if(cadastro) {
                CadastroCartaController cadastroCarta = loader.getController();
                cadastroCarta.setDados(tipo);
            } else {
                LoginContoller loginContoller = loader.getController();
                loginContoller.setTipo(tipo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        createStage(title, root, event);
    }

    /**
     * Método de mudança de cena.
     * <p>
     *  É declarada uma variável root (Parent) e inicializada como null. Essa variável será usada para armazenar
     *  o conteúdo raiz da nova cena.
     * </p>
     * <p>
     *  O bloco try-catch captura qualquer exceção que pode acontecer ao abrir o FXML.
     * </p>
     * <p>
     *  Recebe ainda um parâmetro do tipo cadastro, indicando se vai ou não para tela de cadastro.
     * </p>
     * @param event (ActionEvent) Botão pressionado.
     * @param fxmlFile (‘String’) Nome do arquivo fxml.
     * @param title ('String') Titulo da cena.
     * @param cadastro (boolean) true se for para cadastro e false se não for.
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, boolean cadastro) {

        Parent root = null;

            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile),
                        ResourceBundle.getBundle("resources"));

                root = loader.load();
                if(cadastro) {
                    OpcaoController opcaoController = loader.getController();
                    opcaoController.setCadastro(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        createStage(title, root, event);
    }

    /**
     * Método de mudança de cena.
     * <p>
     *  É declarada uma variável root (Parent) e inicializada como null. Essa variável será usada para armazenar
     *  o conteúdo raiz da nova cena.
     * </p>
     * <p>
     *  O bloco try-catch captura qualquer exceção que pode acontecer ao abrir o FXML.
     * </p>
     * <p>
     *  Trata um evento de mouse (caso da tela inicial).
     * </p>
     * @param event (MouseEvent) click do mouse.
     * @param fxmlFile (‘String’) Nome do arquivo fxml.
     * @param title ('String') Titulo da cena.
     */
    public static void changeScene(MouseEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile),
                    ResourceBundle.getBundle("resources"));

            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        createStage(title, root, event);
    }

    /**
     * Método que cria uma nova janela com base nos parâmetros fornecidos.
     * <p>
     *  Coloca um título na janela, não deixa ser redimensionável e define o tamanho da tela.
     * </p>
     * @param title (String) Título da tela.
     * @param root (Parent) armazena o conteúdo da raiz da nova cena.
     * @param event (ActionEvent) evento para mudança de tela.
     */
    private static void createStage(String title, Parent root, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    /**
     * Método que cria uma nova janela com base nos parâmetros fornecidos.
     * <p>
     *  Coloca um título na janela, não deixa ser redimensionável e define o tamanho da tela.
     * </p>
     * <p>
     *  A diferença do outro método é que esse trata de um KeyEvent (evento do teclado).
     * </p>
     * @param title (String) Título da tela.
     * @param root (Parent) armazena o conteúdo da raiz da nova cena.
     * @param event (KeyEvent) evento para mudança de tela.
     */
    private static void createStage(String title, Parent root, KeyEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    /**
     * Método que cria uma nova janela com base nos parâmetros fornecidos.
     * <p>
     *  Coloca um título na janela, não deixa ser redimensionável e define o tamanho da tela.
     * </p>
     * <p>
     *  A diferença do outro método é que esse trata de um MouseEvent (evento do mouse).
     * </p>
     * @param title (String) Título da tela.
     * @param root (Parent) armazena o conteúdo da raiz da nova cena.
     * @param event (MouseEvent) evento para mudança de tela.
     */
    private static void createStage(String title, Parent root, MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
