package poo.trabalhofinal.supertrunfo.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import poo.trabalhofinal.supertrunfo.classes.cartas.Gato;
import poo.trabalhofinal.supertrunfo.classes.cartas.LinguagensProgramacao;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;
import poo.trabalhofinal.supertrunfo.classes.exceptions.InformacaoInvalidaException;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepository;
import poo.trabalhofinal.supertrunfo.classes.interfaces.CartasRepositoryImpl;
import poo.trabalhofinal.supertrunfo.classes.utils.Util;
import poo.trabalhofinal.supertrunfo.gui.DBUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * <h1>Classe CadastroCartaController <h1/>
 * <p>
 *  Classe responsável por intermediar a relação entre a interface (GUI) da <i>Tela de cadastro de carta</i> e o programa.
 *  </p>
 *  <p>
 *  Recebe as solicitações da interface e trata os eventos de acordo com o esperado no programa.
 *  Permite que o usuário faça cadastro de uma nova carta para poder jogar com ela depois, armazenando seus dados no banco de dados de cartas.
 *  </p>
 *  <p>
 *  Implementa a interface <i>Initializable</i> do JavaFX, que define a assinatura do método de inicialização de um
 *  controller da tela.
 * </p>
 */
public class CadastroCartaController implements Initializable {
    /**
     * Elemento FXML que pega o nome da carta por meio da GUI.
     */
    @FXML
    public TextField nome;
    /**
     * Elemento FXML que pega a classificação da carta por meio da GUI.
     */
    @FXML
    public TextField classificacao;
    /**
     * Elemento FXML que pega a primeira característica da carta por meio da GUI.
     */
    @FXML
    public TextField caracteristica1;
    /**
     * Elemento FXML que pega a segunda característica da carta por meio da GUI.
     */
    @FXML
    public TextField caracteristica2;
    /**
     * Elemento FXML que pega a terceira característica da carta por meio da GUI.
     */
    @FXML
    public TextField caracteristica3;
    /**
     * Elemento FXML que pega a quarta característica da carta por meio da GUI.
     */
    @FXML
    public TextField caracteristica4;
    /**
     * Elemento FXML que pega a quinta característica da carta por meio da GUI.
     */
    @FXML
    public TextField caracteristica5;
    /**
     * Elemento FXML que pega a url da imagem da carta por meio da GUI.
     */
    @FXML
    public TextField imagem;
    /**
     * Elemento FXML que coloca uma mensagem de erro na tela caso haja algum problema na hora de cadastrar a carta.
     * Mostra que o usuário conseguiu cadastrar também.
     */
    @FXML
    public Label alerta;
    /**
     * Elemento FXML (botão) que ao ser clicado permite que a carta seja cadastrada no banco de dados.
     */
    @FXML
    public Button adicionar;
    /**
     * Elemento FXML (botão) que ao ser clicado permite que o usuário retorne ao menu.
     */
    @FXML
    public Button voltar;
    /**
     * ('String') atributo privado que indica o tipo de jogo (baralho).
     */
    String tipo;

    /**
     * Método que modifca o texto presente no prompt do Text Field de acordo com o tipo da carta, colcoando
     * cada característica do respectivo tipo de carta.
     * @param tipo ('String') que representa o tipo de jogo (baralho).
     */
    public void setDados(String tipo) {
        this.tipo = tipo;
        if (tipo.equals("Personagem")) {
            caracteristica1.setPromptText("Inteligência");
            caracteristica2.setPromptText("Força");
            caracteristica3.setPromptText("Coragem");
            caracteristica4.setPromptText("Primeira aparição");
            caracteristica5.setPromptText("Altura");
        } else if (tipo.equals("Gato")) {
            caracteristica1.setPromptText("Agilidade");
            caracteristica2.setPromptText("Fofura");
            caracteristica3.setPromptText("Idade");
            caracteristica4.setPromptText("Agressividade");
            caracteristica5.setPromptText("Peso (kg)");
        } else {
            caracteristica1.setPromptText("Escritabilidade");
            caracteristica2.setPromptText("Legibilidade");
            caracteristica3.setPromptText("Confiabilidade");
            caracteristica4.setPromptText("Custo");
            caracteristica5.setPromptText("Salário Sênior");
        }
    }

    /**
     *  Método sobrescrito oriundo da interface <i>Initializable</i>.
     *  <p>
     *  Método que verifica o tipo e chama o método de conexão com banco da dados de carta, presente na classe CartasRepositoryImpl.
     *  Coloca um alerta na tela caso tenha havido algum erro ou mensagem de cadastrado com sucesso.
     *  </p>
     *  <p>
     *  Chama um método que verifica se todos os campos estão preenchidos, caso não estejam e haja erro, limpam o que já foi preenchido para usuário
     *  preencher novamente.
     *  </p>
     * @param url (URL) do elemento fxml que está sendo carregado.
     * @param resourceBundle (ResourceBundle) é fornecido como convenção para permitir o acesso a recursos adicionais.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adicionar.setOnAction(event -> adicionarCarta(event, null));

        caracteristica5.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                adicionarCarta(null, event);
        });

        voltar.setOnAction(event -> DBUtils.changeScene(event, "menu.fxml", "MENU"));
    }
  
    public void adicionarCarta(ActionEvent event, KeyEvent keyEvent) {
        try {
            validaPreenchidos();
            validaValores();
            if (tipo.equals("Personagem")) {
                CartasRepository<Personagem> cartasRepository = new CartasRepositoryImpl<Personagem>();
                cartasRepository.insereNovaCarta(new Personagem(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                        Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                        Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
            } else if (tipo.equals("Gato")) {
                CartasRepository<Gato> cartasRepository = new CartasRepositoryImpl<Gato>();
                cartasRepository.insereNovaCarta(new Gato(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                        Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                        Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
            } else {
                CartasRepository<LinguagensProgramacao> cartasRepository = new CartasRepositoryImpl<LinguagensProgramacao>();
                cartasRepository.insereNovaCarta(new LinguagensProgramacao(nome.getText(), imagem.getText(), false, Util.stringToClassificacao(classificacao.getText()),
                        Integer.parseInt(caracteristica1.getText()), Integer.parseInt(caracteristica2.getText()), Integer.parseInt(caracteristica3.getText()),
                        Integer.parseInt(caracteristica4.getText()), Double.parseDouble(caracteristica5.getText())));
            }
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("CADASTRO DE CARTA");
            alerta.setContentText("Carta cadastrada com sucesso");
            alerta.showAndWait();

            if (event != null)
                DBUtils.changeScene(event, "menu.fxml", "MENU");
            else
                DBUtils.changeScene(keyEvent, "menu.fxml", "MENU");

        } catch (InformacaoInvalidaException | SQLException e) {
            if (!e.getMessage().contains("Preencha")) {
                nome.setText("");
                classificacao.setText("");
                caracteristica1.setText("");
                caracteristica2.setText("");
                caracteristica3.setText("");
                caracteristica4.setText("");
                caracteristica5.setText("");
                imagem.setText("");
                alerta.setText(e.getMessage());
            }
        }
    }

  /**
     * Método que valida se todos os campos foram preenchidos.
     * @throws InformacaoInvalidaException Caso um dos campos não tenham sido preenchidos.
     */
    private void validaPreenchidos() throws InformacaoInvalidaException {
        if (nome.getText().equals("") && classificacao.getText().equals("") && caracteristica1.getText().equals("")
                && caracteristica2.getText().equals("") && caracteristica3.getText().equals("") && caracteristica4.getText().equals("")
                && caracteristica5.getText().equals(""))
            throw new InformacaoInvalidaException("Preencha todos os campos.");
    }

    /**
     * Método que valida se os dados inseridos nos campos estão válidos.
     * @throws InformacaoInvalidaException Caso um dos dados sejam inválidos.
     */
    private void validaValores() throws InformacaoInvalidaException {
        if(tipo.equals("Personagem")) {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 100 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 2023 || Integer.parseInt(caracteristica4.getText()) < 1000 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");

        } else if(tipo.equals("Gato")) {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 230 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 100 || Integer.parseInt(caracteristica4.getText()) < 0 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");
        } else {
            if (Integer.parseInt(caracteristica1.getText()) > 100 || Integer.parseInt(caracteristica1.getText()) < 0 ||
                    Integer.parseInt(caracteristica2.getText()) > 100 || Integer.parseInt(caracteristica2.getText()) < 0 ||
                    Integer.parseInt(caracteristica3.getText()) > 100 || Integer.parseInt(caracteristica3.getText()) < 0 ||
                    Integer.parseInt(caracteristica4.getText()) > 100 || Integer.parseInt(caracteristica4.getText()) < 0 ||
                    Double.parseDouble(caracteristica5.getText()) < 0)
                throw new InformacaoInvalidaException("Valores inválidos.");
        }
    }
}
