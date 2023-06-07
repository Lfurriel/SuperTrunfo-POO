package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class JogoController implements Initializable {

    //JOGADOR A ---------------------------
    @FXML
    public ImageView imagemA;
    @FXML
    public Label classificacaoA;
    @FXML
    public Label nomeA;
    @FXML
    public Label qtdCartasA;
    @FXML
    public Label usuarioA;
    @FXML
    public Label caracteristicaA1;
    @FXML
    public Label valorA1;
    @FXML
    public Button b_caracteristicaA1;
    @FXML
    public Label caracteristicaA2;
    @FXML
    public Label valorA2;
    @FXML
    public Button b_caracteristicaA2;
    @FXML
    public Label caracteristicaA3;
    @FXML
    public Label valorA3;
    @FXML
    public Button b_caracteristicaA3;
    @FXML
    public Label caracteristicaA4;
    @FXML
    public Label valorA4;
    @FXML
    public Button b_caracteristicaA4;
    @FXML
    public Label caracteristicaA5;
    @FXML
    public Label valorA5;
    @FXML
    public Button b_caracteristicaA5;
    //-------------------------------------

    //JOGADOR B ---------------------------
    @FXML
    public ImageView imagemB;
    @FXML
    public Label classificacaoB;
    @FXML
    public Label nomeB;
    @FXML
    public Label qtdCartasB;
    @FXML
    public Label usuarioB;
    @FXML
    public Label caracteristicaB1;
    @FXML
    public Label valorB1;
    @FXML
    public Button b_caracteristicaB1;
    @FXML
    public Label caracteristicaB2;
    @FXML
    public Label valorB2;
    @FXML
    public Button b_caracteristicaB2;
    @FXML
    public Label caracteristicaB3;
    @FXML
    public Label valorB3;
    @FXML
    public Button b_caracteristicaB3;
    @FXML
    public Label caracteristicaB4;
    @FXML
    public Label valorB4;
    @FXML
    public Button b_caracteristicaB4;
    @FXML
    public Label caracteristicaB5;
    @FXML
    public Label valorB5;
    @FXML
    public Button b_caracteristicaB5;
    //-------------------------------------

    //TODO: pendente botão e imagem do super_trunfo

    private static final String tipo = DBUtils.getTipoJogo();
    private static Jogo jogo = DBUtils.getJogo();
    private Carta topoA;
    private Carta topoB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
        pegaTopo();
        mostrarCartaA();
        mostrarCartaB();

    }

    private void setLabel() {
        usuarioA.setText(jogo.getJogadorA().getNome());
        usuarioB.setText(jogo.getJogadorB().getNome());

        if (tipo.equals("Personagem")) {
            //JOGADOR A ---------------------------
            caracteristicaA1.setText("Inteligência:");
            caracteristicaA2.setText("Força:");
            caracteristicaA3.setText("Coragem:");
            caracteristicaA4.setText("Primeira aparição:");
            caracteristicaA5.setText("Altura:");

            //JOGADOR B ---------------------------
            caracteristicaB1.setText("Inteligência:");
            caracteristicaB2.setText("Força:");
            caracteristicaB3.setText("Coragem:");
            caracteristicaB4.setText("Primeira aparição:");
            caracteristicaB5.setText("Altura:");

        } else if (tipo.equals("Gato")) {
            //JOGADOR A ---------------------------
            caracteristicaA1.setText("Agilidade:");
            caracteristicaA2.setText("Fofura:");
            caracteristicaA3.setText("Idade:");
            caracteristicaA4.setText("Agressividade:");
            caracteristicaA5.setText("Peso:");

            //JOGADOR B ---------------------------
            caracteristicaB1.setText("Agilidade:");
            caracteristicaB2.setText("Fofura:");
            caracteristicaB3.setText("Idade:");
            caracteristicaB4.setText("Agressividade:");
            caracteristicaB5.setText("Peso:");
        } else {
            //JOGADOR A ---------------------------
            caracteristicaA1.setText("Escritabilidade:");
            caracteristicaA2.setText("Legibilidade:");
            caracteristicaA3.setText("Confiabilidade:");
            caracteristicaA4.setText("Custo:");
            caracteristicaA5.setText("Salário sênior:");

            //JOGADOR B ---------------------------
            caracteristicaB1.setText("Escritabilidade:");
            caracteristicaB2.setText("Legibilidade:");
            caracteristicaB3.setText("Confiabilidade:");
            caracteristicaB4.setText("Custo:");
            caracteristicaB5.setText("Salário sênior:");
        }
    }

    private void mostrarCartaA() {
        nomeA.setText(topoA.getNome());
        classificacaoA.setText(topoA.getClassificacao().toString());
        imagemA.setImage(new Image(topoA.getImagem()));
        qtdCartasA.setText(String.valueOf(jogo.getJogadorA().getCartas().size()));

        if (tipo.equals("Personagem")) {
            valorA1.setText(((Personagem) topoA).getInteligencia().toString());
            valorA2.setText(((Personagem) topoA).getForca().toString());
            valorA3.setText(((Personagem) topoA).getCoragem().toString());
            valorA4.setText(((Personagem) topoA).getPrimeiraAparicao().toString());
            valorA5.setText(((Personagem) topoA).getAltura().toString());

        } else if (tipo.equals("Gato")) {
            valorA1.setText(((Gato) topoA).getAgilidade().toString());
            valorA2.setText(((Gato) topoA).getFofura().toString());
            valorA3.setText(((Gato) topoA).getTempoDeVida().toString());
            valorA4.setText(((Gato) topoA).getAgressividade().toString());
            valorA5.setText(((Gato) topoA).getPeso().toString());
        } else {
            valorA1.setText(((LinguagensProgramacao) topoA).getEscritabilidade().toString());
            valorA2.setText(((LinguagensProgramacao) topoA).getLegibilidade().toString());
            valorA3.setText(((LinguagensProgramacao) topoA).getConfiabilidade().toString());
            valorA4.setText(((LinguagensProgramacao) topoA).getCusto().toString());
            valorA5.setText(((LinguagensProgramacao) topoA).getSalarioSenior().toString());
        }
    }

    private void mostrarCartaB() {
        nomeB.setText(topoB.getNome());
        classificacaoB.setText(topoB.getClassificacao().toString());
        imagemB.setImage(new Image(topoB.getImagem()));
        qtdCartasB.setText(String.valueOf(jogo.getJogadorB().getCartas().size()));

        if (tipo.equals("Personagem")) {
            valorB1.setText(((Personagem) topoB).getInteligencia().toString());
            valorB2.setText(((Personagem) topoB).getForca().toString());
            valorB3.setText(((Personagem) topoB).getCoragem().toString());
            valorB4.setText(((Personagem) topoB).getPrimeiraAparicao().toString());
            valorB5.setText(((Personagem) topoB).getAltura().toString());

        } else if (tipo.equals("Gato")) {
            valorB1.setText(((Gato) topoB).getAgilidade().toString());
            valorB2.setText(((Gato) topoB).getFofura().toString());
            valorB3.setText(((Gato) topoB).getTempoDeVida().toString());
            valorB4.setText(((Gato) topoB).getAgressividade().toString());
            valorB5.setText(((Gato) topoB).getPeso().toString());
        } else {
            valorB1.setText(((LinguagensProgramacao) topoB).getEscritabilidade().toString());
            valorB2.setText(((LinguagensProgramacao) topoB).getLegibilidade().toString());
            valorB3.setText(((LinguagensProgramacao) topoB).getConfiabilidade().toString());
            valorB4.setText(((LinguagensProgramacao) topoB).getCusto().toString());
            valorB5.setText(((LinguagensProgramacao) topoB).getSalarioSenior().toString());
        }
    }

    private void pegaTopo() {
        if (tipo.equals("Personagem")) {
            this.topoA = (Personagem) jogo.getJogadorA().getCartas().get(0);
            this.topoB = (Personagem) jogo.getJogadorB().getCartas().get(0);
        } else if (tipo.equals("Gato")) {
            this.topoA = (Gato) jogo.getJogadorA().getCartas().get(0);
            this.topoB = (Gato) jogo.getJogadorB().getCartas().get(0);
        } else {
            this.topoA = (LinguagensProgramacao) jogo.getJogadorA().getCartas().get(0);
            this.topoB = (LinguagensProgramacao) jogo.getJogadorB().getCartas().get(0);
        }
    }
}
