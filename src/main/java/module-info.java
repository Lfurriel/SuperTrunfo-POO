/**
 * Fornece a aplicação principal e classes relacionadas.
 *
 * <h3>Requisitos:</h3>
 * <ul>
 *   <li>{@code javafx.controls} - Biblioteca JavaFX para controles gráficos.</li>
 *   <li>{@code javafx.fxml} - Biblioteca JavaFX para carregamento e manipulação de arquivos FXML.</li>
 *   <li>{@code java.sql} - Biblioteca Java para acesso a banco de dados SQL.</li>
 *   <li>{@code jbcrypt} - Biblioteca para criptografia de senhas utilizando o algoritmo BCrypt.</li>
 *   <li>{@code java.desktop} - Pacote Java para recursos de área de trabalho, como integração com o sistema de arquivos.</li>
 * </ul>
 *
 * <p>Este módulo fornece a aplicação principal SuperTrunfo e outras classes relacionadas necessárias para o funcionamento do jogo.</p>
 */
module com.example.supertrunfo {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;


    opens poo.trabalhofinal.supertrunfo to javafx.fxml;
    opens poo.trabalhofinal.supertrunfo.gui.controllers to javafx.fxml;
    exports poo.trabalhofinal.supertrunfo;
    exports poo.trabalhofinal.supertrunfo.gui.controllers;
    exports poo.trabalhofinal.supertrunfo.classes.cartas;
    exports poo.trabalhofinal.supertrunfo.gui;
    opens poo.trabalhofinal.supertrunfo.gui to javafx.fxml;

}