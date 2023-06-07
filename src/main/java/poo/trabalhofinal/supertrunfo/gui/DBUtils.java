package poo.trabalhofinal.supertrunfo.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poo.trabalhofinal.supertrunfo.HelloApplication;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.exceptions.JogoException;
import poo.trabalhofinal.supertrunfo.gui.controllers.*;

import java.io.IOException;
import java.sql.SQLException;

public class DBUtils {
    private static Jogo jogo;

    public static <T> void iniciaJogo(Jogador<?> jogadorA, Jogador<?> jogadorB, String tipo) throws SQLException, JogoException {
        jogo = new Jogo(jogadorA, jogadorB, tipo);
    }

    public static Jogo getJogo() {
        return jogo;
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
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String tipo, boolean cadastro) {

        Parent root = null;

        try {
            //FXMLLoader loader = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlFile)));
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));

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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, boolean cadastro) {

        Parent root = null;

            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public static void changeScene(MouseEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}