# Desafio LuizaLabs Java
## Objetivo
Construir um projeto Java capa de ler um arquivo de Logs gerado por um FPS (first-person shooter), processar e construir uma análise de jogadores, kills por jogador e kills por partida.

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

## Testes
### Descrição
Foi criado testes unitários para garantir que as pequenas unidades de códigos funcionais estejam funcionando de acordo com previsto.
### Como Testar
Executar o comando utilizando maven
```
mvn test
```
### Como gerar o relatório de cobertura
```
mvn jacoco:prepare-agent test install jacoco:report
```
### Cobertura Atual
![image](https://github.com/FelipeJhordan/luizalabs-java-challenge/assets/44248690/a457614b-32ca-4194-b376-15c376999f41)

## Swagger / Open API 
As funcionalidades da aplicação podem ser acessadas utilizando chamadas REST via métodos HTTP.
### Como acessar
#### Formato JSON
http://localhost:8080/api-docs
#### Web
http://localhost:8080/swagger-ui/index.html
