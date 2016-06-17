# nota-fiscal-api
Sistema exemplo com [Spring Boot](http://projects.spring.io/spring-boot).

## Ferramentas
Ferramentas utilizadas no sistema:
 * [Spring Boot v1.3.5.RELEASE](http://projects.spring.io/spring-boot/)
 * [Spring Boot Data JPA](http://projects.spring.io/spring-data-jpa/)
 * [Spring Boot Data Rest](http://projects.spring.io/spring-data-rest/)
 * [Spring Boot JMS](https://spring.io/guides/gs/messaging-jms/)
 * [Apache ActiveMQ Broker v5.13.3](http://activemq.apache.org/)
 * [H2 Database v1.4.192](http://www.h2database.com/html/main.html)
 * [Angular v1.5.6](https://github.com/angular/bower-angular)
 * [Angular Route v1.5.6](https://github.com/angular/bower-angular-route)
 * [Bootstrap v3.3.6](https://github.com/twbs/bootstrap)
 
## Ferramentas de Teste
Ferramentas utilizadas para os testes:
 * [Spring Boot Starter Test](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
 
## Rodar
```
$> mvn spring-boot:run
```

## Testar
```
$> mvn test
```

## Sobre
### Funcionalidade
Temos duas entidades principais no sistema:
 * `NotaFiscal`: Representa uma nota fiscal no sistema.
 * `Mercadoria`: Representa uma mercadoria no sistema.
 
Além das duas entidades temos o atributo `NotaFiscal#itens` que representa
os itens de uma nota fiscal, portanto, o atributo é um `Set` de `Mercadoria`.

### Arquitetura
O sistema roda em um [Tomcat8](https://tomcat.apache.org/download-80.cgi)
embarcado que disponibiliza seus recursos de negócio através de endpoints
REST. 

É utilizado o Spring Data Rest para disponizar os endpoints diretamente
da interface repositório do recurso de negócio, ou seja, entidade.

Temos o banco de dados H2 Database embarcado para armazenas as entidades
do sistema.

O sistema também disponibiliza duas filas JMS através de um Broker ActiveMQ
embarcado para inclusão de mercadorias e notas fiscais.

Para o tratamento de erro de processamento nas filas foi adotada uma política
de retentativas padrão do ActiveMQ e envio para a DLQ (Death Letter Queue) após.

As mensagens enviadas para a DLQ são consumidas e é gerado um registro no
log do sistema. Esse log pode ser utilizado para processamento de estatísticas,
dashboards, relatórios, entre outros índices.

### Acessando AngularJS
Para acessar a página home em AngularJS que disponibiliza uma listagem de
mercadorias e notas fiscais:
 * _URL_: `http://localhost:8080/`

### Acessando Endpoints
Para acessar o endpoint raiz:
 * _URL_: `http://localhost:8080/api`
```
$> curl -v http://localhost:8080/api
```
Para acessar o endpoint de listagem de mercadorias:
 * _URL_: `http://localhost:8080/api/mercadorias`
```
$> curl -v http://localhost:8080/api/mercadorias
```
Para acessar o endpoint de listagem de notas fiscais:
 * _URL_: `http://localhost:8080/api/notasfiscais`
```
$> curl -v http://localhost:8080/api/notasfiscais
```
Para acessar o endpoint de detalhe de uma mercadoria:
 * _URL_: `http://localhost:8080/api/mercadorias/:id`
```
$> curl -v http://localhost:8080/api/mercadorias/1
```
Para acessar o endpoint de listagem de notas fiscais:
 * _url_: `http://localhost:8080/api/notasfiscais/:id`
```
$> curl -v http://localhost:8080/api/notasfiscais/1
```
 
Para acessar o endpoint de listagem de itens de uma nota fiscal:
 * _url_: `http://localhost:8080/api/notasfiscais/:id/itens`
```
$> curl -v http://localhost:8080/api/notasfiscais/:id/itens
```
 
Para incluir uma mercadoria:
```
$> curl -i -v -X POST -H "Content-Type: application/json" \
   -d '{"codigo": "MER999", "descricao": "Mercadoria 999", "valor": 999.99}' \
   http://localhost:8080/api/mercadoria
```

Para incluir uma nota fiscal:
```
$> curl -i -v -X POST -H "Content-Type: application/json" \
   -d '{"codigo": "NF999", "descricao": "Nota Fiscal 999", "itens": ["http://localhost:8080/api/mercadoria/1"]}' \
   http://localhost:8080/api/notafiscal
```
 
### Acessando ActiveMQ
Para acessar a fila de inclusão de mercadorias:
 * _URI_: `vm://localhost` 
 * _QUEUE_: `mercadoria-mailbox`

Para acessar a fila de inclusão de notas fiscais:
 * _URI_: `vm://localhost` 
 * _QUEUE_: `notafiscal-mailbox`

