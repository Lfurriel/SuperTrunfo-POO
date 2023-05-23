package poo.trabalhofinal.supertrunfo.classes;

import poo.trabalhofinal.supertrunfo.classes.cartas.Carta;
import poo.trabalhofinal.supertrunfo.classes.cartas.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    ArrayList<?> baralho;
    Jogador jogador1;
    Jogador jogador2;

    /**
     * Método construtor do jogo, buscamos no banco de dados as cartas usando o primeiro parâmetro e intânciamos os dois
     * jogadores.
     *
     * @param jogo
     * @param jogador1
     * @param jogador2
     */
    public Jogo(String jogo, Jogador jogador1, Jogador jogador2) throws SQLException {
        this.baralho = buscaCartasDB(jogo);
        Collections.shuffle(baralho);
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        int tamanhoBaralho = baralho.size();
        int metade = tamanhoBaralho / 2;

        jogador1.setCartas((List<Carta>) baralho.subList(0, metade));
        jogador2.setCartas((List<Carta>) baralho.subList(metade, tamanhoBaralho));
    }

    private ArrayList<?> buscaCartasDB(String jogo) throws SQLException {
        Connection conexao = null;
        PreparedStatement query;
        ResultSet resultSet = null;

        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataLake", "postgres", "FurriSenha");
            query = conexao.prepareStatement("SELECT * FROM cartas WHERE tipo = ?");
            query.setString(1, jogo);
            resultSet = query.executeQuery();

            List<Carta> cartas = new ArrayList<>();
            if (jogo.equals("Personagem")) {
                while (resultSet.next()) {
                    Personagem personagem = new Personagem();
                    personagem.setNome(resultSet.getString("nome"));
                    personagem.setImagem(resultSet.getString("imagem"));
                    personagem.setClassificacao(resultSet.getString("classificacao"));
                    personagem.setSuperTrunfo(resultSet.getString("super_trunfo"));
                    personagem.setInteligencia(Integer.valueOf(resultSet.getString("atributo1")));
                    personagem.setForca(Integer.valueOf(resultSet.getString("atributo2")));
                    personagem.setCoragem(Integer.valueOf(resultSet.getString("atributo3")));
                    personagem.setPrimeiraAparicao(Integer.valueOf(resultSet.getString("atributo4")));
                    personagem.setAltura(Double.valueOf(resultSet.getString("atributo4")));

                    System.out.println(personagem);
                    cartas.add(personagem);
                }

                System.out.println(cartas.size());
                return (ArrayList<?>) cartas;
            } else if (jogo.equals("")) {
                //TODO:
            }
            //ResultSet insert = conexao.createStatement().executeQuery("INSERT INTO cartas values (2, 'Personagem', 'Juloia', 'imagem da juloia', true, 100, 30, 73, 2002, 1.70);");
        } catch (SQLException e) {
            throw new SQLException("Erro com banco de dados!");
        } finally {
            if (conexao != null)
                conexao.close();
        }

        return null;
    }

    public void addBaralho(ArrayList<?> baralho) {
        this.baralho = baralho;
    }

    public void jogarPersonagem() {
        Scanner sc = new Scanner(System.in);
        int turno = 0;
        boolean continua = true;

        while (continua) {

            Personagem topoA = (Personagem) jogador1.getCartas().get(0);
            Personagem topoB = (Personagem) jogador2.getCartas().get(0);

            int escolha = 0;
            boolean superTrunfo = false;

            if (turno % 2 == 0) { //Turno jogador 1

                if (topoA.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoA.comparaInteligencia(topoB);
                } else if (escolha == 2) {
                    compara = topoA.comparaForca(topoB);
                } else if (escolha == 3) {
                    compara = topoA.comparaCoragem(topoB);
                } else if (escolha == 4) {
                    compara = topoA.comparaAparicao(topoB);
                } else if (escolha == 5) {
                    compara = topoA.comparaAltura(topoB);
                }

                if (superTrunfo || compara == 1) { // VENCEU ROUND
                    jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-5);
                } else if (compara == -1) { // PERDEU ROUND
                    //jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-10); // Perde mais ponto pois perdeu na própria rodada
                } else { // EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }

            } else {
                //Turno jogador 2
                if (topoB.isSuperTrunfo()) {
                    //SuperTrunfo = vitória
                    superTrunfo = true;
                } else {
                    escolha = sc.nextInt();
                }
                int compara = 0;

                if (escolha == 1) {
                    compara = topoB.comparaInteligencia(topoA);
                } else if (escolha == 2) {
                    compara = topoB.comparaForca(topoA);
                } else if (escolha == 3) {
                    compara = topoB.comparaCoragem(topoA);
                } else if (escolha == 4) {
                    compara = topoB.comparaAparicao(topoA);
                } else if (escolha == 5) {
                    compara = topoB.comparaAltura(topoA);
                }

                if (superTrunfo || compara == 1) { //VENCEU ROUND
                    //jogador2.moveCartas(jogador1);

                    jogador2.pontua(10);
                    jogador1.pontua(-5);
                } else if (compara == -1) { //PERDEU
                    //jogador1.moveCartas(jogador2);

                    jogador1.pontua(10);
                    jogador2.pontua(-10); //Perde mais ponto pois perdeu na propria rodada
                } else { //EMPATE
                    jogador1.moveTopo();
                    jogador2.moveTopo();
                }
            }
            turno++;
            if (jogador1.getCartas().size() == 0 || jogador2.getCartas().size() == 0)
                continua = false;
        }
    }


}
