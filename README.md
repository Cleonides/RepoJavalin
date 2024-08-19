# JAVALIN(microframework) COM REDIS(banco em memória)

<p align="center">
  <img src="caminho/para/imagem.png" alt="Imagem do Projeto" width="300"/>
</p>

<p align="center">
  <a href="https://github.com/usuario/repositorio/actions">
    <img src="https://img.shields.io/badge/build-passing-brightgreen" alt="Status de Build"/>
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/license-MIT-blue" alt="Licença MIT"/>
  </a>
</p>

## Introdução

<p>Um crud de usuários usando javalin(microframework) com redis(É um armazenamento de estrutura de dados em memória, usado como um banco de dados em memória distribuído de chave-valor, cache e agente de mensagens, com durabilidade opcional). Para testar a aplicação usei o postman.</p>

## Tabela de Conteúdos

<ul>
  <li><a href="#introdução">Introdução</a></li>
  <li><a href="#instalação">Instalação</a></li>
  <li><a href="#uso">Uso</a></li>
<!--   <li><a href="#contribuição">Contribuição</a></li>
  <li><a href="#licença">Licença</a></li> -->
</ul>

## Instalação
1- Baixe o projeto : url(https://github.com/Cleonides/RepoJavalin.git)  <p></p>

2- Rode o script.sh (cmd ou terminal) entre na pasta raiz do projeto e execute : ./script.sh <p></p>
#Precisa atualizar a versão do docker-compose atual(v2.28.1): <p></p>
2.1 - Se for Linux : <p></p>
Precisa dar permissão para executar o arquivo script.sh: chmod + x ./script.sh 
sudo rm /usr/local/bin/docker-compose ou /usr/bin/docker-compose<br />
sudo curl -L "https://github.com/docker/compose/releases/download/v2.28.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose<br />
sudo chmod +x /usr/local/bin/docker-compose <p></p>
3-Após subir aplicação esse é o endereço : http://localhost:8080/  ou http://0.0.0.0.:8080/  <p></p>
Aparece a mensagem : Server in the air, time (Servidor no ar, horário)+ horário da máquina. <p></p>


## Uso
Se usar via postman ou insomnia essas são as urls : <br/>
GET -  http://localhost:/persons<br/>
POST - http://localhost:/persons <br/>
 Em body - raw - json: {<br/>
       "name": "antonio",<br/>
       "age":16<br/>
       } <p></p>
PUT - http://localhost:/persons/id<br/>
 Em body - raw - json: {<br/>
 "name": "antonio",<br/>
 "age":16<br/>
 } <p></p>

DEL - http://localhost:/persons/id<br/>
GET DEFAULT - http://localhost:/ <br/>
  #Servidor no ar, horário :18/08/2024 01:42:01# <p></p>

Via curl use os comandos para fazer o CRUD <br/>
#Criar usuarios   <p></p>
Windows : <p></p>
curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"Luquinha\", \"age\": 19}" localhost:8080/persons <br/>
curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"Pedrinho\", \"age\": 29}" localhost:8080/persons <br/>
curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"Antonio\", \"age\":  39}" localhost:8080/persons <br/>
<p></p>
Linux :<p></p> 
curl -X POST -H "Content-Type: application/json" -d '{"name": "Luquinha", "age": 19}' localhost:8080/persons<br/>
curl -X POST -H "Content-Type: application/json" -d '{"name": "Pedrinho", "age": 29}' localhost:8080/persons<br/>
curl -X POST -H "Content-Type: application/json" -d '{"name": "Antonio", "age": 39}' localhost:8080/persons<br/>
<p></p>
#Consultar usuarios  <br/>
CURL -X GET -i http://localhost:8080/persons  <p></p>

#Atualizar usuarios <br/>
Windows:<p></p>
curl -X PUT -H "Content-Type: application/json" -d "{\"name\": \"Julninho\", \"age\": 19}" localhost:8080/persons/1  <br/>
<p></p>
Linux:<p></p>
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Julninho", "age": 19}' localhost:8080/persons/1  <p></p>

#Deletar usuarios <br/>
curl -v -X DELETE http://localhost:8080/persons/7 <br/>
```bash 

 
