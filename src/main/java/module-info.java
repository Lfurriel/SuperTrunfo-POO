module com.example.supertrunfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens poo.trabalhofinal.supertrunfo to javafx.fxml;
    exports poo.trabalhofinal.supertrunfo;
    exports poo.trabalhofinal.supertrunfo.classes.cartas;

}