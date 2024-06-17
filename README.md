# Desafio LuizaLabs Java
- [Objetivo](#objetivo)
- [Ferramentas](#ferramentas)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Utilitários e ferramentas utilizadas](#utilitários-e-ferramentas-utilizadas)
- [Como executar](#como-executar)
  - [Requisitos](#requisitos)
  - [Passos](#passos)
- [Swagger / Open API](#swagger--open-api)
  - [Como acessar](#como-acessar)
- [Organização e Arquitetura](#organização-e-arquitetura)
- [Regras adicionais](#regras-adicionais)
- [Testes](#testes)
  - [Descrição](#descrição)
  - [Como Testar](#como-testar)
  - [Como gerar o relatório de cobertura](#como-gerar-o-relatório-de-cobertura)
  - [Cobertura Atual](#cobertura-atual)
## Objetivo
Construir um projeto Java capa de ler um arquivo de Logs gerado por um FPS (first-person shooter), processar e construir uma análise de jogadores, kills por jogador e kills por partida.
- [X] Java 17
- [X] Documentação seguindo OPEN API 3.0
- [X] Gravar no banco de dados
- [X] Testes unitários
- [X] Docker
## Ferramentas 
### Tecnologias Utilizadas
- Java 17.x
- Mongodb
- Docker
- Maven
- Spring 
- Jacoco
- Lombok

### Utilitários e ferramentas utilizadas
- Visual Studio Code
- mongo-express
- Postman

## Como executar
### Requisitos
- Maven
- Docker
- Java 17
- WSL ou distro LINUX

### Passos
Buildar a imagem da aplicação utilizando os passos listados no Dockerfile.
```
docker compose build
```

Inicializar a orquestração da aplicação e do banco de dados.
```
docker compose up
```


## Swagger / Open API 
As funcionalidades da aplicação podem ser acessadas utilizando chamadas REST via métodos HTTP.
### Como acessar
#### Formato JSON
http://localhost:8080/api-docs
#### Web
http://localhost:8080/swagger-ui/index.html

## Organização e Arquitetura
A aplicação foi separada utilizando uma visão inspirada na arquitetura limpa, criando camadas como Presentation ( Entrada/Saída de dados ), Aplicação (  Domínio ( Operações principais que cumprem o objetivo da aplicação ), Aplicação ( funcionalidades e configurações principais ) e Infra ( Ferramentas externas como Gateways/Acesso a banco de dados e disco ). 
Uma das regras para evitar acoplamento foi a utilização de interfaces, principalmente para que camadas mais perto do "domínio" não dependese das camadas mais "externas".

## Regras adicionais
Alguns fluxos lógicos que não estavam na descrição do desafio foram estabelecidos, mas que podem ou não ser alterados:
## Jogador se matar utilizando uma bomba 
Nesse caso o jogador não ganha um kill, mas o total de kills do jogo é incrementado.
## Jogador com kills negativas 
É possível o jogador ficar com KDA negativo caso ela morra pelo mundo e não tenha um saldo que mantenha ele com um número neutro ou positivo.

## Testes
### Descrição
Foi criado testes unitários para garantir que as pequenas unidades de códigos funcionais estejam funcionando de acordo com previsto.
### Como Testar
Executar o comando utilizando maven.
```
mvn test
```
### Como gerar o relatório de cobertura
```
mvn jacoco:prepare-agent test install jacoco:report
```
### Cobertura Atual
![image](https://github.com/FelipeJhordan/luizalabs-java-challenge/assets/44248690/a457614b-32ca-4194-b376-15c376999f41)

