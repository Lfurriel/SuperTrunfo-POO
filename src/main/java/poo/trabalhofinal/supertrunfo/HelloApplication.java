package poo.trabalhofinal.supertrunfo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>Classe HelloApplication</h1>
 * <p>
 * Classe responsável por inicializar os elevemtos do JavaFX. É o ponto de entrada para os aplicativos FXML.
 * </p>
 *
 * @author Lucas Furriel Rodrigues
 * @author Júlia Rodrigues Marques do Nascimento
 * @version 1.0
 */
public class HelloApplication extends Application {
    /**
     * Classe responsável por criar um objeto FXMLLoader para carregar o arquivo FXML da tela inicial.
     * <p>
     * Carrega o arquivo FXML e cria uma cena com tamanho definido, que não é redimensionável.
     * </p>
     * <p>
     * Coloca um ícone em todas as janelas.
     * </p>
     * <p>
     * Responsável por mostrar as cenas na aplicação.
     * </p>
     * @param stage objeto Stage como argumento -> representa a janela principal do aplicativo.
     * @throws IOException Se houver erro ao inicializar o JavaFX
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("telaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setResizable(false);
        stage.getIcons().add(new Image(HelloApplication.class.getResource("/poo/trabalhofinal/supertrunfo/gui/fujutrunfo.png").toExternalForm()));
        stage.setTitle("SUPER TRUNFO");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main que fica responsável por inicializar a aplicação JavaFX.
     * @param args ('String')
     */
    public static void main(String[] args) {
        launch();
    }
}