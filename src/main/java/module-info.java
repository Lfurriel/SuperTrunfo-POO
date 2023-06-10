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