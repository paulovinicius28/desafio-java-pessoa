# Desafio Java - Usuário

## Repositório do  Desafio de Java - API de Manter usuários.

API desenvolvida em Java versão 21, Spring Boot com autenticação e autorização com Spring Security, Banco de dados em memória - H2.

Esta API possui endpoints de login para autenticação, que ao ser efetuado com sucesso, irá gerar um Token para utilização dos demais, que são: consultar todos os usuários, consultar usuário por id, editar usuário e excluir usuário.

## Requisitos para execução:

* Possuir JDK 21 instalado [Link para download aqui!](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2024-12/R/eclipse-jee-2024-12-R-win32-x86_64.zip)
* IDE Eclipse 2024.12 instalado. [Link para download aqui!](https://download.oracle.com/java/21/archive/jdk-21.0.4_windows-x64_bin.exe)
* Instalar no Eclipse a ferramenta Spring Tools 4 (encontrado no Eclipse Marketplace).
* Instalar o POSTMAN para execução das requisições [Link para download aqui!](https://dl.pstmn.io/download/latest/win64)

## Instruções para instalação e execução do projeto
* Importar o projeto no Eclipse, clicando em Import > Aba General > Existing Projects into Workspace.
* Executar o projeto na aba Boot Dashboard.
* Para a execução das requisições, importe os arquivos **Desafio Java.postman_collection.json** e **Desafio Java - Local.postman_environment.json** **(localizadas na pasta raiz do projeto)** no seu Postman para obter todas as configurações de ambiente e execução das mesmas.

## API Endpoints
**IMPORTANTE!**
Para acesso, o sistema deve estar em execução, via Eclipse!

As APIs estão localizadas no Swagger, com seus respectivos detalhes e retornos no endereço: http://localhost:8080/desafio/swagger-ui/index.html

## Acesso a base de dados:

Por ser um ambiente de desenvolvimento, foi criada uma rotina de criação de tabela e registros iniciais para uso. é executado durante a inicialização do servidor de aplicação.

O acesso é feito pela UI do banco H2, que fica no endereço: http://localhost:8080/desafio/h2-console/

**O JDBC URL é o jdbc:h2:mem:desafiojava , o User sa e não há senha.**
