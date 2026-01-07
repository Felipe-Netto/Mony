# 💰 Mony API

API REST completa para gerenciamento financeiro pessoal desenvolvida com **Spring Boot 4.0** e **Java 25**. Implementa autenticação JWT, gerenciamento de contas e transações com cálculo automático de saldo.

## 🚀 Stack Tecnológica

- **Java 25** | **Spring Boot 4.0.0**
- **Spring Security** + **JWT (Auth0)** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Flyway** - Migrações versionadas
- **Docker** - Containerização
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate

## ✨ Funcionalidades

- 🔐 Autenticação JWT com Spring Security
- 👤 CRUD completo de usuários com roles (ADMIN/USER)
- 💰 Gerenciamento de contas financeiras (múltiplos tipos)
- 📊 Sistema de transações (receitas/despesas)
- 💵 Cálculo automático de saldo em tempo real
- 🛡️ Tratamento global de exceções
- 🗄️ Migrações automatizadas com Flyway

## 🏗️ Arquitetura

Projeto estruturado seguindo boas práticas de arquitetura em camadas:

```
├── controllers/     # REST endpoints
├── services/        # Lógica de negócio
├── repositories/    # Acesso a dados (JPA)
├── domains/         # Entidades JPA
├── DTOs/            # Data Transfer Objects
├── infra/           # Security, Exceptions
└── enums/           # Enumeradores
```

## ⚡ Quick Start

### Pré-requisitos
- Java 25+
- Docker & Docker Compose
- Maven 3.6+

### Executando

1. **Inicie o banco de dados:**
```bash
docker-compose up -d
```

2. **Configure a variável de ambiente `JWT_SECRET`

3. **Execute a aplicação:**

A API estará disponível em `http://localhost:8080`

## 📡 Principais Endpoints

### Autenticação
- `POST /auth/register` - Registro de usuário
- `POST /auth/login` - Login (retorna JWT token)
- `GET /auth/me` - Dados do usuário autenticado

### Contas
- `GET /accounts` - Listar contas do usuário
- `POST /accounts` - Criar conta
- `PUT /accounts/{id}` - Atualizar conta
- `DELETE /accounts/{id}` - Deletar conta

### Transações
- `GET /transactions?accountId={id}` - Listar transações
- `POST /transactions` - Criar transação (atualiza saldo automaticamente)
- `GET /transactions/{id}` - Detalhes da transação

**Todos os endpoints (exceto `/auth/register` e `/auth/login`) requerem autenticação via Bearer Token.**

## 🗄️ Banco de Dados

Migrações versionadas com Flyway executadas automaticamente:
- Tabelas: `users`, `accounts`, `transactions`
- Relacionamentos: User → Accounts → Transactions
- Auditoria: timestamps automáticos (createdAt/updatedAt)

## 👨‍💻 Autor

**Felipe Netto**

---

💡 **Destaques técnicos:** Arquitetura em camadas, segurança robusta com JWT, migrações versionadas, tratamento de exceções global e cálculo automático de saldo.
