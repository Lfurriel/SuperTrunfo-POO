package poo.trabalhofinal.supertrunfo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("telaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 420);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setTitle("SUPER TRUNFO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}