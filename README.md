# deposito-bebidas

## `Proposta:`

A proposta do desafio e a criação de uma API RESTful, para gerir dados de armazenamento e estoque de um depósito de bebidas. Atualmente o estoque armazena dois tipos de bebidas (alcoólicas e não alcoólicas), contudo, isto pode mudar no futuro.
O estoque possui 5 seções e cada seção só pode armazenar um tipo de bebida, isto é, não é possivel armazenar ou manter bebidas alcoólicas e não alcoólicas juntas.
Cada seção possui capacidade de armazenamento de 500 litros de bebidas alcoólicas e 400 de não alcoólicas.

### `Responsabilidades API`

`API deverá ser responsável por gerenciar:`

- Cadastro e consulta das bebidas armazenadas em cada secao com suas respectivas queries.
- Consulta do volume total no estoque por cada tipo de bebida.
- Consulta das secoes disponiveis de armazenamento de um determinado volume de bebida. (calcular via algoritmo).
- Consulta das secoes disponiveis para venda de determinado tipo de bebida (calcular via algoritmo).
- Cadastro de historico de entrada e saida de bebidas em caso de venda e recebimento.
- Consulta do historico de entradas e saidas por tipo de bebida e secao.

### `Regras API` 

`Regras para fluxo de cadastro e cálculo:`

- Uma seção não pode ter dois ou mais tipos diferentes de bebidas (como ja fora dito).
- Não há entrada ou saída de estoque sem respectivo registro no histórico.
- Registro deve conter horário, tipo, volume, seção e responsável pela entrada.
- O endpoint de consulta de histórico de entrada e saída de estoque, deve retornar os resultados ordenados por data e seção, podendo alterar a ordenação via parametros.
- Para situações de erro, e necessário que a resposta da requisição seja coerente em exibir uma mensagem condizente com o erro.
    
## `Descrições técnicas`

Projeto construido na linguagem Java utilizando a versão 8. 

Tecnologias utilizadas :

 - Spring Boot.
 - Gerenciador de dependencias e build, Maven.
 - Banco de dados HSQLDB em memória.
 - Swagger para a geração da documentação. 
 - Interface de comunicação REST.

## `Requisitos do sistema`

Requisitos

- Maven
- Java 8

### Gerando o Pacote

Sendo um projeto Maven, execute os goals clean e install na raiz do projeto para baixar as dependências e gerar jar do projeto.

    #!/deposito-bebidas
    $ mvn clean install

Executando o Jar
Como se trata de um projeto Spring Boot, podemos simplismente executar o jar que foi gerado na pasta target e a 
aplicação irá subir em um tomcat embedded.

    #!/deposito-bebidas/target
    $ java -jar deposito-bebidas-0.0.1-SNAPSHOT.jar

Configuração da porta da api se encontra no application.properties:
		
	server.port:9000
		
Pronto, a aplicação deve estar online na porta 9000.

## `Documentação API`

Após iniciar aplicação a documentação gerada com Swagger estará disponível automaticamente no endereço:

	http://localhost:9000/swagger-ui.html

## 	`Banco de dados HSQLDB`

Api possui como banco de dados o HSQLDB em memória, no pom.xml existe a dependência do mesmo e as configurações se encontram no application.properties no caminho:

	#!/deposito-bebidas/src/main/resources/application.properties

Configuração banco de dados api:
	
	spring.datasource.driver-class-name=org.hsqldb.jdbcDriver
	spring.datasource.url=jdbc:hsqldb:mem:.
	spring.datasource.username=dbhsql
	spring.datasource.password=dbhsql
	
## `Exemplos:`

Inserção tipo de bebida:

	curl --request POST http://localhost:9000/bebidas/cadastro
	--header 'Content-Type: application/json' 
	--data '{\n"tipo": "Alcoólicas"\n}'
    
Saída esperada: `201 Created`
	
	{
	  "id": 1,
	  "tipo": "Alcoólicas",
	  "volumeMaximo": 500
	}

Recupera tipos de bebidas:

	curl --request GET http://localhost:9000/bebidas

Saída esperada:

	[
      {
       "id": 1,
       "tipo": "Alcoólicas",
       "volumeMaximo": 500
      }
	]

Inserção de nova seção:

	curl --request POST http://localhost:9000/secoes/cadastro
	--header 'Content-Type: application/json'
	--data '{\n"nome": "A"\n}'
    
Saída esperada: `201 Created`
	
	{
	  "id": 1,
	  "nome": "A",
	  "bebida": null,
	  "volume": 0
	}

Recupera seções:

	curl --request GET http://localhost:9000/secoes

Saída esperada:

	[
      {
        "id": 1,
        "nome": "A",
        "bebida": null,
        "volume": 0
      }
	]
	
Inserção entrada de bebidas em uma seção:

	curl --request POST http://localhost:9000/estoque/entrada
	--header 'Content-Type: application/json'
	--data '{\n"idBebida": 1,\n"idSecao": 1,\n"volume": 500,\n"responsavel": "Luiz"\n}'
  
Saída esperada: `200 OK`
	
	Bebida adicionada ao estoque com sucesso!

Recupera total de volume em estoque por tipo de bebida:

	curl --request GET http://localhost:9000/estoque/total/1

Saída esperada: `200 OK`
	
	Estoque possui volume total de 0 litros de bebidas Alcoólicas!
	
Inserção saída de bebidas em uma seção:

	curl --request PUT http://localhost:9000/estoque/saida
	--header 'Content-Type: application/json'
	--data '{\n"idBebida": 1,\n"idSecao": 1,\n"volume": 75,\n"responsavel": "Luiz"\n}'

Saída esperada: `200 OK`
	
	Bebida retirada do estoque com sucesso!
	
Consulta seções que possuem volume e tipo de bebida informado como parametros para armazenamento:

	curl --request GET http://localhost:9000/estoque/disponiveis/entrada/1/10

Saída esperada: `200 OK`
	
	[
      {
        "id": 1,
        "nome": "A",
        "bebida": {
            "id": 1,
            "tipo": "Alcoólicas",
            "volumeMaximo": 500
        },
        "volume": 490
      }
	]		

Consulta seções que possuem volume e tipo de bebida informado como parametros para venda:

	curl --request GET http://localhost:9000/estoque/disponiveis/saida/1/10

Saída esperada: `200 OK`
	
	[
      {
        "id": 1,
        "nome": "A",
        "bebida": {
            "id": 1,
            "tipo": "Alcoólicas",
            "volumeMaximo": 500
        },
        "volume": 490
      }
	]	