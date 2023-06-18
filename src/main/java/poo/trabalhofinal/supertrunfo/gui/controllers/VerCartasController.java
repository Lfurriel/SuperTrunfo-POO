package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * <h1>Classe VerCartasController</h1>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela para ver cartas</i> e o programa.
 *  </p>
 *  <p>
 *  Mostra na tela todas as cartas do jogo, uma a uma.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class VerCartasController implements Initializable {
    /**
     * Elemento FXML responsável por mostrar uma imagem na tela.
     */
    @FXML
    public ImageView imagem;
    /**
     * Elemento FXML responsável por colocar a imagem de FuJu Trunfo, caso a carta seja um supertrunfo.
     */
    @FXML
    public ImageView trunfo;
    /**
     * Elemento FXML responsável por mostrar a classificação na tela.
     */
    @FXML
    public Label classificacao;
    /**
     * Elemento FXML responsável por mostrar o nome na tela.
     */
    @FXML
    public Label nome;
    /**
     * Elemento FXML responsável por mostrar o valor da primeira característica da carta na tela.
     */
    @FXML
    public Label caracteristica1;
    /**
     * Elemento FXML responsável por mostrar o valor da segunda característica da carta na tela.
     */
    @FXML
    public Label caracteristica2;
    /**
     * Elemento FXML responsável por mostrar o valor da terceira característica da carta na tela.
     */
    @FXML
    public Label caracteristica3;
    /**
     * Elemento FXML responsável por mostrar o valor da quarta característica da carta na tela.
     */
    @FXML
    public Label caracteristica4;
    /**
     * Elemento FXML responsável por mostrar o valor da quinta característica da carta na tela.
     */
    @FXML
    public Label caracteristica5;
    /**
     * Elemento FXML responsável por mostrar qual é a primeira característica da carta na tela.
     */
    @FXML
    public Label c1;
    /**
     * Elemento FXML responsável por mostrar qual é a segunda característica da carta na tela.
     */
    @FXML
    public Label c2;
    /**
     * Elemento FXML responsável por mostrar qual é a terceira característica da carta na tela.
     */
    @FXML
    public Label c3;
    /**
     * Elemento FXML responsável por mostrar qual é a quarta característica da carta na tela.
     */
    @FXML
    public Label c4;
    /**
     * Elemento FXML responsável por mostrar qual é a quinta característica da carta na tela.
     */
    @FXML
    public Label c5;
    /**
     * Elemento FXML que descreve o valor da característica 3.
     */
    @FXML
    public Label descricao3;
    /**
     * Elemento FXML que descreve o valor da característica 4.
     */
    @FXML
    public Label descricao4;
    /**
     * Elemento FXML que descreve o valor da característica 5.
     */
    @FXML
    public Label descricao5;
    /**
     * Elemento FXML que mostra qual o tipo da carta.
     */
    @FXML
    public Label tipo;
    /**
     * Elemento FXML que permite o usuário voltar ao menu.
     */
    @FXML
    public Button sair;
    /**
     * Elemento FXML que permite o usuário ver a carta anterior.
     */
    @FXML
    public Button anterior;
    /**
     * Elemento FXML que permite o usuário ver a carta posterior.
     */
    @FXML
    public Button proximo;
    /**
     * Elemento FXML que coloca uma mensagem de erro na tela caso algo dê errado.
     */
    @FXML
    public Label alerta;
    /**
     * ArrayList que representa a lista de todas as cartas do jogo.
     */
    private ArrayList<Carta> cartas;
    /**
     * Índice para ver uma carta da lista de cartas.
     */
    private static int i = 0;

    /**
     * Método sobrescrito oriundo da interface <i>Initializable</i>.
     * <p>
     *  Tenta conectar com o banco de dados por meio de étodos da classe CartasRepository.
     * </p>
     * <p>
     *  Caso o usuário aperte em um botão próximo, o contador é incrementado. Enquanto não tiver atingido o final da lista de cartas, a carta
     *  aparesentada na tela é mudada para a próxima carta da lista.
     *  Caso atinja seu fim, coloca um alerta na tela falando que chegou ao fim.
     * </p>
     * <p>
     *  Se o usuário apertar no botão de anterior, o contado é decrementado até chegar ao início (primeiro elemento) da lista.
     * </p>
     * <p>
     *  AO cilcar no botão sair, ele retorna para tela de menu.
     * </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CartasRepository<Carta> cartasRepository = new CartasRepositoryImpl<>();
        try {
            cartas = cartasRepository.buscaTodasCartas();
            mostrarCartas(cartas.get(i));
        } catch (SQLException e) {
            alerta.setText(e.getMessage());
        }

        proximo.setOnAction(event -> {

            if (cartas != null && cartas.size() > 0 && i + 1 < cartas.size()) // Para verificar se o próximo índice está dentro da lista
                mostrarCartas(cartas.get(++i));
            else if (cartas != null && i + 1 >= cartas.size())
                alerta.setText("Não tem carta posterior");
            else
                alerta.setText("Erro ao buscar cartas");
        });


        anterior.setOnAction(event -> {
            if (cartas != null && cartas.size() > 0 && i - 1 >= 0)
                mostrarCartas(cartas.get(--i));
            else if (cartas != null && i - 1 < 0)
                alerta.setText("Não tem carta anterior");
            else
                alerta.setText("Erro ao buscar cartas");
        });

        sair.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }

    /**
     * Imprime na tela da GUI (por meio dos Labels) os dados de cada carta.
     * Coloca a imagem da carta na tela.
     * @param carta (Carta) carta que está sendo mostrada
     */
    private void mostrarCartas(Carta carta) {
        alerta.setText("");
        String nomeCarta = carta.getNome().toUpperCase();
        nome.setText(nomeCarta);
        classificacao.setText(String.valueOf(carta.getClassificacao()));
        try {
            URL urlImagem = getClass().getResource(carta.getImagem());
            Image imagemCarta = new Image(urlImagem != null ? urlImagem.toExternalForm() : null);
            imagem.setImage(imagemCarta);
        } catch (Exception e) {
            alerta.setText("Erro ao carregar imagem");
        }
        trunfo.setVisible(carta.isSuperTrunfo());
        if (carta instanceof Personagem) {
            tipo.setText("Personagem");
            c1.setText("Inteligência:");
            c2.setText("Força:");
            c3.setText("Coragem:");
            c4.setText("Primeira aparição:");
            c5.setText("Altura:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((Personagem) carta).getInteligencia()));
            caracteristica2.setText(String.valueOf(((Personagem) carta).getForca()));
            caracteristica3.setText(String.valueOf(((Personagem) carta).getCoragem()));
            caracteristica4.setText(String.valueOf(((Personagem) carta).getPrimeiraAparicao()));
            caracteristica5.setText(String.valueOf(((Personagem) carta).getAltura()));
            descricao3.setText("/100");
            descricao4.setText("(ano)");
            descricao5.setText("(metros)");
        } else if (carta instanceof Gato) {
            tipo.setText("Gato");
            c1.setText("Agilidade:");
            c2.setText("Fofura:");
            c3.setText("Tempo de vida:");
            c4.setText("Agressividade:");
            c5.setText("Peso:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((Gato) carta).getAgilidade()));
            caracteristica2.setText(String.valueOf(((Gato) carta).getFofura()));
            caracteristica3.setText(String.valueOf(((Gato) carta).getTempoDeVida()));
            caracteristica4.setText(String.valueOf(((Gato) carta).getAgressividade()));
            caracteristica5.setText(String.valueOf(((Gato) carta).getPeso()));
            descricao3.setText("(meses)");
            descricao4.setText("/100");
            descricao5.setText("kg");
        } else {
            tipo.setText("Linguagem de programação");
            c1.setText("Escritabilidade:");
            c2.setText("Legibilidade:");
            c3.setText("Confiabilidade:");
            c4.setText("Custo:");
            c5.setText("Salário Sênior:");
            nome.setText(carta.getNome());
            caracteristica1.setText(String.valueOf(((LinguagensProgramacao) carta).getEscritabilidade()));
            caracteristica2.setText(String.valueOf(((LinguagensProgramacao) carta).getLegibilidade()));
            caracteristica3.setText(String.valueOf(((LinguagensProgramacao) carta).getConfiabilidade()));
            caracteristica4.setText(String.valueOf(((LinguagensProgramacao) carta).getCusto()));
            caracteristica5.setText(String.valueOf(((LinguagensProgramacao) carta).getSalarioSenior()));
            descricao3.setText("/100");
            descricao4.setText("/100");
            descricao5.setText("(reais)");
        }
    }
}
