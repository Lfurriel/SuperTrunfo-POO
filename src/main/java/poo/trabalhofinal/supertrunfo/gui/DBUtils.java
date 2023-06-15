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


public class DBUtils {

    private static Image icon = new Image(HelloApplication.class.getResource("/poo/trabalhofinal/supertrunfo/gui/fujutrunfo.png").toExternalForm());
    private static Jogo jogo;
    private static String tipoJogo;

    private static Jogador vencedor;
    private static Jogador perdedor;
    private static boolean empatou;

    public static void setFimPartida(Jogador a, Jogador b, boolean empate){
        vencedor = a;
        perdedor = b;
        empatou = empate;
    }
    public static Jogador getVencedor() {
        return vencedor;
    }

    public static Jogador getPerdedor() {
        return perdedor;
    }

    public static boolean getEmpate() {
        return empatou;
    }

    public static <T> void iniciaJogo(Jogador<?> jogadorA, Jogador<?> jogadorB, String tipo) throws SQLException, JogoException {
        jogo = new Jogo(jogadorA, jogadorB, tipo);
        tipoJogo = tipo;
    }

    public static Jogo getJogo() {
        return jogo;
    }

    public static String getTipoJogo() {
        return tipoJogo;
    }

    /**
     * Método de mudança de cena
     * @param event Botão pressionado
     * @param fxmlFile - Nome do arquivo fxml
     * @param title - Titulo da cena
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

    private static void createStage(String title, Parent root, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    private static void createStage(String title, Parent root, KeyEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    private static void createStage(String title, Parent root, MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
