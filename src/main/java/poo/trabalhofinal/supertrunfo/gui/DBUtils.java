package poo.trabalhofinal.supertrunfo.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poo.trabalhofinal.supertrunfo.HelloApplication;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.gui.controllers.CadastroCartaController;
import poo.trabalhofinal.supertrunfo.gui.controllers.JogoController;
import poo.trabalhofinal.supertrunfo.gui.controllers.MenuController;
import poo.trabalhofinal.supertrunfo.gui.controllers.OpcaoController;

import java.io.IOException;
import java.util.Objects;

public class DBUtils {



    /**
     * Método de mudança de cena
     * @param event Botão pressionado
     * @param fxmlFile - Nome do arquivo fxml
     * @param title - Titulo da cena
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlFile)));
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

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String tipo) {

        Parent root = null;

        try {
            //FXMLLoader loader = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlFile)));
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));

            root = loader.load();
            CadastroCartaController cadastroCarta = loader.getController();
            cadastroCarta.setDados(tipo);
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
