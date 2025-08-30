# DIO Bank DDD — API bancária com Java/Spring (DDD) + Postgres em Docker

### **Agradecimento**

> Projeto desenvolvido no contexto dos bootcamps da **Digital Innovation One (DIO)**.
> Obrigado, DIO, por incentivar o estudo prático e a construção de portfólio no Brasil! 💙

---

## Sobre

API bancária didática para consolidar **POO** e **DDD**, com operações de:

- **Accounts**: criar conta, depósito, saque, PIX e histórico de transações
- **Investments**: criar investimento vinculado a uma conta
- Persistência em **PostgreSQL**
- Documentação **OpenAPI/Swagger**
- **Docker** para tudo (não precisa Java nem Maven instalados)

**Stack:** Java 21 · Spring Boot 3.5 · Spring Web · Spring Data JPA · Bean Validation · PostgreSQL · Springdoc OpenAPI

---

## Arquitetura (DDD)

```plaintext
src/main/java/com/dio/bank/bank_ddd
├─ domain/ # Regras de negócio + contratos
│ ├─ Account.java
│ ├─ AccountType.java
│ ├─ Investment.java
│ ├─ InvestmentType.java
│ ├─ Transaction.java
│ ├─ TransactionType.java
│ └─ repo/
│   ├─ AccountRepository.java
│   ├─ InvestmentRepository.java
│   └─ TransactionRepository.java
├─ application/ # Orquestra casos de uso
│   ├─ AccountService.java
│   └─ InvestmentService.java
├─ infra/ # Adaptadores para o mundo externo (JPA/DB)
│   ├─ jpa/
│   │ ├─ SpringDataAccountRepository.java
│   │ ├─ SpringDataInvestmentRepository.java
│   │ └─ SpringDataTransactionRepository.java
│   └─ adapter/
│     ├─ AccountRepositoryAdapter.java
│     ├─ InvestmentRepositoryAdapter.java
│     └─ TransactionRepositoryAdapter.java
└─ interfaces/ # Interface com o “mundo”: REST, DTOs, exception handler
  ├─ controller/
  │ ├─ AccountController.java
  │ └─ InvestmentController.java
  ├─ dto/
  │ ├─ AccountDtos.java
  │ └─ InvestmentDtos.java
  ├─ config/
  │ └─ OpenApiConfig.java
  └─ ExceptionHandlerAdvice.java
```

## Pré-requisitos

- **Docker** e **Docker Compose** instalados
- Não é necessário ter Java/Maven localmente

---

## Como rodar

1. **Clone o repositório**

```bash
  git clone https://github.com/AlexandreNoguez/spring-ddd-pattern
  cd bank-ddd
```

2. Suba os containers (app + Postgres)

```bash
docker compose up -d --build
```

3. Acompanhe os logs

```bash
docker compose logs -f app
```

Quando aparecer Tomcat started on port 8080, a API está pronta.

Teste rápido de saúde

```bash
curl -i http://localhost:8080/actuator/health
```

Parar e remover volumes

```bash
docker compose down # só para
docker compose down -v # para + apaga dados do Postgres
```

## Configuração

Arquivo: src/main/resources/application.properties

spring.application.name=bank-ddd
spring.datasource.url=jdbc:postgresql://db:5432/dio_bank
spring.datasource.username=dio
spring.datasource.password=dio
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080

## O docker-compose.yml prepara o ambiente com:

- db (PostgreSQL 16) em localhost:5432

- app (Spring Boot) em localhost:8080

- Documentação (Swagger / OpenAPI)

- UI: http://localhost:8080/swagger-ui.html (pode redirecionar para /swagger-ui/index.html, é normal)

- Docs JSON: http://localhost:8080/v3/api-docs

- Se o navegador não renderizar (cache/extensões), acesse diretamente:
  (aqui[http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config])
  e confira os assets (devem responder 200):
  /swagger-ui/swagger-ui-bundle.js e /swagger-ui/swagger-ui.css.

## Testando com VSCode (REST Client)

- Arquivo api.http na raiz, com variáveis e exemplos.
  Passos:

- Instale a extensão REST Client (Huachao Mao) no VSCode

- Abra api.http

- Clique em “Send Request” acima de cada requisição

### Ordem sugerida:

Criar Conta → 2) Depositar → 3) Sacar → 4) PIX → 5) Criar Investimento → 6) Listar Transações

Dica: copie o id retornado na criação da conta e ajuste a variável @accountId no topo do api.http.

## Endpoints incluídos:

POST /api/accounts (criar conta)

POST /api/accounts/deposit (depósito)

POST /api/accounts/withdraw (saque)

POST /api/accounts/pix (PIX por chave)

POST /api/investments (criar investimento)

GET /api/accounts/{accountId}/transactions (listar transações)

## Resumo dos bodies

Criar Conta
{
"type": "CHECKING",
"holderName": "Lara",
"cpf": "12345678900",
"pixKey": "lara@pix"
}

Depositar
{ "accountId": "UUID", "amount": 150.00 }

Sacar
{ "accountId": "UUID", "amount": 50.00 }

PIX
{ "fromPixKey": "alex@pix", "toPixKey": "lara@pix", "amount": 75.00 }

Criar Investimento
{ "accountId": "UUID", "type": "CDB", "amount": 200.00 }

Listar Transações
GET /api/accounts/{accountId}/transactions

Dicas & Troubleshooting

Swagger UI não renderiza mas /v3/api-docs responde:
use a URL com configUrl (acima), limpe cache/abra modo anônimo e desative ad-blockers.

Porta 8080 ocupada: ajuste server.port em application.properties.

DB inicial: usuário/senha dio · database dio_bank (config no compose).

## Licença

Projeto educacional para portfólio. Sinta-se à vontade para usar e evoluir. ✨
