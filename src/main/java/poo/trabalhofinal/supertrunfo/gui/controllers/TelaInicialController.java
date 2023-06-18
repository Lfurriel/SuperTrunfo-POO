package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>Classe TelaInicialController</h1>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela Inicial</i> e o programa.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class TelaInicialController implements Initializable {
    /**
     * AnchorPane (Layout/Contêiner responsável pela posição dos elementos de forma absoluta dentro dele) pane: elemento
     * no qual se encontram os elementos da interface na Tela Inicial -> uado para capturar o mouse event.
     */
    @FXML
    public AnchorPane pane;

    /**
     * Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     *  Define que ao clicar com mouse na tela (AnchorPane) o evento seja capturado e a tela mude para a tela de menu.
     * </p>
     * @param location (URL) do elemento fxml que está sendo carregado.
     * @param resources (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setOnMouseClicked(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }
}