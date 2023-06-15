package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poo.trabalhofinal.supertrunfo.classes.Jogador;
import poo.trabalhofinal.supertrunfo.classes.cartas.Classificacao;
import poo.trabalhofinal.supertrunfo.classes.Jogo;
import poo.trabalhofinal.supertrunfo.classes.cartas.*;
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
    @FXML
    public Button b_superA;
    @FXML
    public ImageView superA;
    @FXML
    public Label alertaA;
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
    @FXML
    public Button b_superB;
    @FXML
    public ImageView superB;
    @FXML
    public Label alertaB;
    //-------------------------------------
    @FXML
    public Label turno;
    @FXML
    public ImageView fundo;
    @FXML
    public Button render;
    @FXML
    public Button encerrar;
    @FXML
    public Button empatar;

    final URL urlFundoA = getClass().getResource("/poo/trabalhofinal/supertrunfo/gui/jogoA.jpg");
    final Image fundoA = new Image(urlFundoA != null ? urlFundoA.toExternalForm() : null);

    final URL urlFundoB = getClass().getResource("/poo/trabalhofinal/supertrunfo/gui/jogoB.jpg");
    final Image fundoB = new Image(urlFundoB != null ? urlFundoB.toExternalForm() : null);

    private static final String tipo = DBUtils.getTipoJogo();
    private static final Jogo jogo = DBUtils.getJogo();
    private Carta topoA;
    private Carta topoB;
    private int rodada = 0;
    private int ultimoA;
    private int ultimoB;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
        pegaTopo();
        setTurno(new ActionEvent());

        //Botões de A
        b_superA.setOnAction(event -> {
            if (topoB.getClassificacao().compareTo(Classificacao.A5) < 0) {
                //Jogar A vence o turno
                jogo.getJogadorA().pontua(10);
                jogo.getJogadorB().pontua(-5);

                jogo.getJogadorA().moveCartas(jogo.getJogadorB());

                verificaVencedor(event, jogo.getJogadorA());
            } else {
                //Jogar B vence o turno
                jogo.getJogadorA().pontua(-10);
                jogo.getJogadorB().pontua(10);

                jogo.getJogadorB().moveCartas(jogo.getJogadorA());

                verificaVencedor(event, jogo.getJogadorB());
            }
        });

        b_caracteristicaA1.setOnAction(event -> {
            if (ultimoA == 1) {
                alertaA.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC1();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();
                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-5);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-10);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoA = 1;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaA2.setOnAction(event -> {
            if (ultimoA == 2) {
                alertaA.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC2();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-5);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-10);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoA = 2;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaA3.setOnAction(event -> {
            if (ultimoA == 3) {
                alertaA.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC3();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-5);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-10);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoA = 3;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaA4.setOnAction(event -> {
            if (ultimoA == 4) {
                alertaA.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC4();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-5);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-10);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoA = 4;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaA5.setOnAction(event -> {
            if (ultimoA == 5) {
                alertaA.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC5();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-5);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-10);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoA = 5;
                verificaVencedor(event, vencedor);
            }
        });

        b_superB.setOnAction(event -> {
            if (topoA.getClassificacao().compareTo(Classificacao.A5) < 0) {
                //Jogar B vence o turno
                jogo.getJogadorA().pontua(-5);
                jogo.getJogadorB().pontua(10);

                jogo.getJogadorB().moveCartas(jogo.getJogadorA());

                verificaVencedor(event, jogo.getJogadorB());
            } else {
                //Jogar A vence o turno
                jogo.getJogadorA().pontua(10);
                jogo.getJogadorB().pontua(-10);

                jogo.getJogadorA().moveCartas(jogo.getJogadorB());

                verificaVencedor(event, jogo.getJogadorA());
            }
        });

        b_caracteristicaB1.setOnAction(event -> {
            if (ultimoB == 1) {
                alertaB.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC1();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-10);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-5);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoB = 1;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaB2.setOnAction(event -> {
            if (ultimoB == 2) {
                alertaB.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC2();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-10);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-5);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoB = 2;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaB3.setOnAction(event -> {
            if (ultimoB == 3) {
                alertaB.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC3();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-10);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-5);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoB = 3;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaB4.setOnAction(event -> {
            if (ultimoB == 4) {
                alertaB.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC4();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-10);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-5);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoB = 4;
                verificaVencedor(event, vencedor);
            }
        });

        b_caracteristicaB5.setOnAction(event -> {
            if (ultimoB == 5) {
                alertaB.setText("Você não pode jogar o mesmo atributo duas vezes");
            } else {
                Jogador vencedor = vencedorRodadaC5();
                if (vencedor == null) {
                    jogo.getJogadorA().moveTopo();
                    jogo.getJogadorB().moveTopo();

                } else if (vencedor == jogo.getJogadorA()) {
                    jogo.getJogadorA().pontua(10);
                    jogo.getJogadorB().pontua(-10);

                    jogo.getJogadorA().moveCartas(jogo.getJogadorB());
                } else {
                    jogo.getJogadorA().pontua(-5);
                    jogo.getJogadorB().pontua(10);

                    jogo.getJogadorB().moveCartas(jogo.getJogadorA());
                }
                ultimoB = 5;
                verificaVencedor(event, vencedor);
            }
        });

        render.setOnAction(event -> {
            if (rodada % 2 == 1) //Jogador A se rendeu
                DBUtils.setFimPartida(jogo.getJogadorB(), jogo.getJogadorA(), false);
            else //Jogador B se rendeu
                DBUtils.setFimPartida(jogo.getJogadorA(), jogo.getJogadorB(), false);
            DBUtils.changeScene(event, "vencedor.fxml", "VENCEDOR");
        });

        encerrar.setOnAction(event -> {
            if (jogo.getJogadorB().getCartas().size() > jogo.getJogadorA().getCartas().size()) //Jogador A se rendeu
                DBUtils.setFimPartida(jogo.getJogadorB(), jogo.getJogadorA(), false);
            else if (jogo.getJogadorA().getCartas().size() > jogo.getJogadorB().getCartas().size()) //Jogador B se rendeu
                DBUtils.setFimPartida(jogo.getJogadorA(), jogo.getJogadorB(), false);
            else
                DBUtils.setFimPartida(jogo.getJogadorA(), jogo.getJogadorB(), true);
            DBUtils.changeScene(event, "vencedor.fxml", "VENCEDOR");
        });

        empatar.setOnAction(event -> {
            DBUtils.setFimPartida(jogo.getJogadorA(), jogo.getJogadorB(), true);
            DBUtils.changeScene(event, "vencedor.fxml", "VENCEDOR");
        });
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

    private void mostrarCartaA(boolean mostraB) {
        String nome = topoA.getNome().toUpperCase();
        nomeA.setText(nome);
        classificacaoA.setText(topoA.getClassificacao().toString());

        try {
            URL urlImagem = getClass().getResource(topoA.getImagem());
            Image imagemCarta = new Image(urlImagem != null ? urlImagem.toExternalForm() : null);
            imagemA.setImage(imagemCarta);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem!");
        }

        qtdCartasA.setText(String.valueOf(jogo.getJogadorA().getCartas().size()));
        b_superA.setDisable(!topoA.isSuperTrunfo());

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
            valorA5.setText("R$ " + ((LinguagensProgramacao) topoA).getSalarioSenior().toString());
        }

        // Esconde ou mostra a carta do jogador B
        nomeB.setVisible(mostraB);
        imagemB.setVisible(mostraB);
        classificacaoB.setVisible(mostraB);
        superB.setVisible(mostraB && topoB.isSuperTrunfo());
        caracteristicaB1.setVisible(mostraB);
        valorB1.setVisible(mostraB);
        caracteristicaB2.setVisible(mostraB);
        valorB2.setVisible(mostraB);
        caracteristicaB3.setVisible(mostraB);
        valorB3.setVisible(mostraB);
        caracteristicaB4.setVisible(mostraB);
        valorB4.setVisible(mostraB);
        caracteristicaB5.setVisible(mostraB);
        valorB5.setVisible(mostraB);
    }

    private void mostrarCartaB(boolean mostraA) {
        String nome = topoB.getNome().toUpperCase();
        nomeB.setText(nome);
        classificacaoB.setText(topoB.getClassificacao().toString());

        try {
            URL urlImagem = getClass().getResource(topoB.getImagem());
            Image imagemCarta = new Image(urlImagem != null ? urlImagem.toExternalForm() : null);
            imagemB.setImage(imagemCarta);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem!");
        }

        qtdCartasB.setText(String.valueOf(jogo.getJogadorB().getCartas().size()));
        b_superB.setDisable(!topoB.isSuperTrunfo());

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
            valorB5.setText("R$ " + ((LinguagensProgramacao) topoB).getSalarioSenior().toString());
        }

        // Esconde ou mostra a carta do jogador A
        nomeA.setVisible(mostraA);
        imagemA.setVisible(mostraA);
        classificacaoA.setVisible(mostraA);
        superA.setVisible(mostraA && topoA.isSuperTrunfo());
        caracteristicaA1.setVisible(mostraA);
        valorA1.setVisible(mostraA);
        caracteristicaA2.setVisible(mostraA);
        valorA2.setVisible(mostraA);
        caracteristicaA3.setVisible(mostraA);
        valorA3.setVisible(mostraA);
        caracteristicaA4.setVisible(mostraA);
        valorA4.setVisible(mostraA);
        caracteristicaA5.setVisible(mostraA);
        valorA5.setVisible(mostraA);
    }

    private void setTurno(ActionEvent event) {

        alertaA.setText("");
        alertaB.setText("");

        rodada++;
        if (rodada % 2 == 1) {
            try {
                fundo.setImage(fundoA);

                mostrarCartaA(false);
                mostrarCartaB(true);
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERRO AO CARREGAR IMAGEM DE FUNDO");
                alerta.setContentText(e.getLocalizedMessage());
                alerta.showAndWait();

                DBUtils.changeScene(event, "menu.fxml", "MENU");
            }
            turno.setText("Turno: " + rodada + " - " + jogo.getJogadorA().getNome());
            //Habilitando os botões do jogador A
            b_caracteristicaA1.setDisable(false);
            b_caracteristicaA2.setDisable(false);
            b_caracteristicaA3.setDisable(false);
            b_caracteristicaA4.setDisable(false);
            b_caracteristicaA5.setDisable(false);

            //Desabilitando botões do jogador B
            b_caracteristicaB1.setDisable(true);
            b_caracteristicaB2.setDisable(true);
            b_caracteristicaB3.setDisable(true);
            b_caracteristicaB4.setDisable(true);
            b_caracteristicaB5.setDisable(true);
            b_superB.setDisable(true);
        } else {
            try {
                fundo.setImage(fundoB);
                mostrarCartaA(true);
                mostrarCartaB(false);
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERRO AO CARREGAR IMAGEM DE FUNDO");
                alerta.setContentText(e.getLocalizedMessage());
                alerta.showAndWait();

                DBUtils.changeScene(event, "menu.fxml", "MENU");
            }
            turno.setText("Turno: " + rodada + " - " + jogo.getJogadorB().getNome());
            //Desabilitando botões do jogador A
            b_caracteristicaA1.setDisable(true);
            b_caracteristicaA2.setDisable(true);
            b_caracteristicaA3.setDisable(true);
            b_caracteristicaA4.setDisable(true);
            b_caracteristicaA5.setDisable(true);
            b_superA.setDisable(true);

            //Habilitando os botões do jogador B
            b_caracteristicaB1.setDisable(false);
            b_caracteristicaB2.setDisable(false);
            b_caracteristicaB3.setDisable(false);
            b_caracteristicaB4.setDisable(false);
            b_caracteristicaB5.setDisable(false);
        }
    }

    private Jogador vencedorRodadaC1() {
        int compara;
        if (tipo.equals("Personagem"))
            compara = ((Personagem) topoA).comparaInteligencia((Personagem) topoB);
        else if (tipo.equals("Gato"))
            compara = ((Gato) topoA).comparaAgilidade((Gato) topoB);
        else
            compara = ((LinguagensProgramacao) topoA).comparaEscritabilidade((LinguagensProgramacao) topoB);

        if (compara > 0)
            return jogo.getJogadorA();
        else if (compara < 0)
            return jogo.getJogadorB();
        else
            return null;

    }

    private Jogador vencedorRodadaC2() {
        int compara;
        if (tipo.equals("Personagem"))
            compara = ((Personagem) topoA).comparaForca((Personagem) topoB);
        else if (tipo.equals("Gato"))
            compara = ((Gato) topoA).comparaFofura((Gato) topoB);
        else
            compara = ((LinguagensProgramacao) topoA).comparaLegibilidade((LinguagensProgramacao) topoB);

        if (compara > 0)
            return jogo.getJogadorA();
        else if (compara < 0)
            return jogo.getJogadorB();
        else
            return null;
    }

    private Jogador vencedorRodadaC3() {
        int compara;
        if (tipo.equals("Personagem"))
            compara = ((Personagem) topoA).comparaCoragem((Personagem) topoB);
        else if (tipo.equals("Gato"))
            compara = ((Gato) topoA).comparaVida((Gato) topoB);
        else
            compara = ((LinguagensProgramacao) topoA).comparaConfiabilidade((LinguagensProgramacao) topoB);

        if (compara > 0)
            return jogo.getJogadorA();
        else if (compara < 0)
            return jogo.getJogadorB();
        else
            return null;
    }

    private Jogador vencedorRodadaC4() {
        int compara;
        if (tipo.equals("Personagem"))
            compara = ((Personagem) topoA).comparaAparicao((Personagem) topoB);
        else if (tipo.equals("Gato"))
            compara = ((Gato) topoA).comparaAgressividade((Gato) topoB);
        else
            compara = ((LinguagensProgramacao) topoA).comparaCusto((LinguagensProgramacao) topoB);

        if (compara > 0)
            return jogo.getJogadorA();
        else if (compara < 0)
            return jogo.getJogadorB();
        else
            return null;
    }

    private Jogador vencedorRodadaC5() {
        int compara;
        if (tipo.equals("Personagem"))
            compara = ((Personagem) topoA).comparaAltura((Personagem) topoB);
        else if (tipo.equals("Gato"))
            compara = ((Gato) topoA).comparaPeso((Gato) topoB);
        else
            compara = ((LinguagensProgramacao) topoA).comparaSalario((LinguagensProgramacao) topoB);

        if (compara > 0)
            return jogo.getJogadorA();
        else if (compara < 0)
            return jogo.getJogadorB();
        else
            return null;
    }

    private void verificaVencedor(ActionEvent event, Jogador vencedor) {

        mostrarCartaA(true);
        mostrarCartaB(true);

        if (jogo.getJogadorA().getCartas().size() == 0) {
            DBUtils.setFimPartida(jogo.getJogadorB(), jogo.getJogadorA(), false);
            DBUtils.changeScene(event, "vencedor.fxml", "VENCEDOR");
        } else if (jogo.getJogadorB().getCartas().size() == 0) {
            DBUtils.setFimPartida(jogo.getJogadorA(), jogo.getJogadorB(), false);
            DBUtils.changeScene(event, "vencedor.fxml", "VENCEDOR");
        } else {
            //Informa quem venceu o truno
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            if (vencedor != null) {
                alerta.setTitle("Vencedor");
                alerta.setContentText("Vencedor: " + vencedor.getNome() + "!");
            } else {
                alerta.setTitle("Empate");
                alerta.setContentText("Essa rodada empatou!");
            }

            alerta.showAndWait();

            pegaTopo();
            setTurno(event);
        }
    }
}
