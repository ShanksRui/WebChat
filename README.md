 
# Projeto desenvolvido com Spring Boot + MongoDB, aplicando o deploy no Render.
# WebChat API (Spring Boot + MongoDB/MongoDB-Atlas)
 
*DIAGRAMA UML*
API REST para gerenciamento de **usuários**, **conversas** e **mensagens** de um chat.
 
-![DIAGRAMA UML](WebChat.drawio.png)
> Projeto Java com Spring Boot, persistência em MongoDB e deploy containerizado (Docker) no Render.

Este projeto implementa uma API de chat com os seguintes recursos:
- Cadastro e gerenciamento de usuários.
- Criação automática de chat entre dois usuários.
- Envio de mensagens dentro do chat.
- Busca de chat por par de usuários.
- Busca de mensagens por texto.
- Busca de usuários por nome e por status.

A aplicação está preparada para rodar com **MongoDB Atlas** em nuvem usando variável de ambiente (`SPRING_DATA_MONGODB_URI`) e já possui **Dockerfile** para deploy em plataformas como Render.

##  Stack e tecnologias
- Java 17
- Spring Boot 3.5.7
- Spring Web
- Spring Data MongoDB
- Spring Validation
- springdoc OpenAPI (Swagger UI)
- Maven
- Docker
- Postman

##  Estrutura principal do projeto

```
src/main/java/com/development/webchat
├── Config
├── model/entities
├── repositories
├── resources                # Controllers REST
├── resources/exceptions     # Tratamento global de exceções
└── services/exceptions
```

##  Modelo de dados (resumo)

### User
- id: String
- name: String
- password: String
- status: Status (OFFLINE, ONLINE, DO_NOT_DISTURB, ABSENT)
- chats: List<Chat> (referência)

### Chat
- id: String
- user0Id: String
- user1Id: String
- messages: List<Message>
- firstChat: Instant (momento da primeira mensagem)
- lastActivity: Instant (momento da última atividade)

> Existe índice composto único para o par (user0Id, user1Id), evitando duplicidade de chat com a mesma ordem.

### Message (embutida em Chat)
- authorMsg: { id, name }
- text: String
- mommentMsg: Instant

##  Status de usuário

O enum Status também pode ser buscado por código numérico na API:

- 1 → OFFLINE
- 2 → ONLINE
- 3 → DO_NOT_DISTURB
- 4 → ABSENT

## Configuração de ambiente

### Profiles

- application.properties define profile ativo como prod.
- Em prod, a URI do Mongo é lida da variável:

```properties
SPRING_DATA_MONGODB_URI
```

### Variáveis esperadas

| Variável | Obrigatória | Exemplo |
|---|---|---|
| SPRING_DATA_MONGODB_URI | Sim | mongodb+srv://user:pass@cluster.mongodb.net/webchat?retryWrites=true&w=majority |

> A aplicação expõe a porta 8080 no container.

##  Como rodar localmente

### Pré-requisitos

- Java 17+
- Maven 3.9+
- MongoDB Atlas (ou Mongo local com URI compatível)


Aplicação disponível em:

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html


## Endpoints da API

## Usuários (`/users`)

| Método | Rota | Descrição |
|---|---|---|
| GET | /users | Lista todos os usuários |
| GET | /users/{id} | Busca usuário por id |
| POST | /users | Cria usuário |
| PATCH | /users/{id} | Atualiza parcialmente usuário |
| DELETE | /users/{id} | Remove usuário |
| GET | /users/searchname?name=... | Busca usuários por nome (contains, case-insensitive) |
| GET | /users/searchstatus?status=1..4 | Busca usuários por status |

### Exemplo - criar usuário

POST /users

```json
{
  "name": "joao",
  "password": "12345",
  "status": "ONLINE"
}
```

## Chats (/chats)

| Método | Rota | Descrição |
|---|---|---|
| GET | /chats | Lista todos os chats |
| GET | /chats/{id} | Busca chat por id |
| POST | /chats/{id0}/{id1} | Cria chat (ou reutiliza existente) e adiciona mensagem |
| DELETE | /chats/{id} | Remove chat |
| GET | /chats/searchchat?id0=...&id1=... | Busca chat entre dois usuários |
| GET | /chats/searchmessage?text=... | Busca mensagens por texto |

### Exemplo - enviar mensagem no chat

POST /chats/{id0}/{id1}

```json
{
  "authorMsg": {
    "id": "<id-do-autor>",
    "name": "joao"
  },
  "text": "Fala pessoal!",
  "mommentMsg": "2026-01-10T12:00:00Z"
}
```

##  Tratamento de erros

A API retorna:

- 404 para recursos não encontrados (usuário/chat inexistente, etc.).
- 400 para erros de validação de DTO.

Formato padrão (StandardError) para exceções de negócio:

```json
{
  "timestamp": "2026-01-10T12:00:00Z",
  "status": 404,
  "error": "not Found",
  "msgError": "Id:abc not found",
  "path": "/users/abc"
}
```

##  Dados de seed

Há uma classe de seed (`Seeding`) ativada no profile `test` que popula usuários e chat inicial para testes locais.
