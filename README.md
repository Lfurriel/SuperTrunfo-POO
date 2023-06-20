# SuperTrunfo-POO

O projeto final "SuperTrunfo-POO" é um jogo de cartas desenvolvido em Java, utilizando o framework JavaFX para criar a interface gráfica. O jogo é baseado no "Super Trunfo" e possui diferentes baralhos, como personagens, gatos e linguagens de programação. O projeto utiliza um banco de dados PostgreSQL para armazenar informações do jogo, como jogadores (nome, senha e pontuação) e cartas (nome, classificação, imagem e atributos). O objetivo do projeto é aplicar conceitos de programação orientada a objetos e oferecer uma experiência interativa e divertida aos jogadores.

## Bibliotecas usadas:

- ```javafx.controls``` - Biblioteca JavaFX para controles gráficos.
- ```javafx.fxml``` - Biblioteca JavaFX para carregamento e manipulação de arquivos FXML.
- ```java.sql``` - Biblioteca Java para acesso a banco de dados SQL.
- ```jbcrypt``` - Biblioteca para criptografia de senhas utilizando o algoritmo BCrypt.
- ```java.desktop``` - Pacote Java para recursos de área de trabalho, como integração com o sistema de
arquivos.

## Banco de Dados:

Usamos para esse projeto um banco de dados local em PostrgreSQL. As configurações de acesso ao banco estão salvas no arquivo resource.properties em *"/src/main/resources/resources.properties"* (**importante:** a senha está omitida por questões de segurança).

## Configurações necessárias:

- Versão jdk: 20.0.1.
- O Projeto irá compliar e rodar normalmente com as configurações padrões do Java, porém ele como um todo não funcionará corretamente uma vez que tudo funciona pegando informações do banco de dados.

## Observações:
- As imagens das cartas estão salvas no projeto no path *"/src/main/resources/imagens/"*
- O projeto está todo documentado no formato Doxygen e o javadocs em formato .pdf esta no path *"/src/main/resources/files/javadocs.pdf"*
- No path *"/src/main/resources/files/"* podem ser encontrados o arquivo de UML e e as imagens geradas todas pela IDE IntelliJ
- No mesmo path pode ser encontrado um arquivo chamado "telaDeExplicacao.pdf", este é uma documentação sobre o funcionamento das telas da GUI
