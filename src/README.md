# 🎮 Backlog de Games API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)

Uma API RESTful desenvolvida em Java e Spring Boot para o gerenciamento de um catálogo pessoal de jogos. O projeto foi construído do zero focando em arquitetura profissional, segurança e boas práticas de mercado.

## ✨ Funcionalidades

- **Vitrine Pública:** Qualquer usuário pode consultar o catálogo de jogos e categorias.
- **Estoque Restrito:** Apenas usuários autenticados possuem permissão para criar, editar ou excluir jogos e categorias.
- **Sistema de Contas:** Cadastro de novos usuários com senhas criptografadas (BCrypt).
- **Autenticação Segura:** Geração e validação de Tokens JWT para gerenciamento de sessões *Stateless*.
- **Integridade de Dados:** Validação rigorosa na entrada de dados e tratamento global de exceções para respostas amigáveis.

## 🛠️ Tecnologias e Padrões Utilizados

- **Java 17+**
- **Spring Boot** (Web, Data JPA, Security, Validation)
- **PostgreSQL** (Banco de dados de produção)
- **Auth0 java-jwt** (Criação e validação de tokens JWT)
- **Springdoc OpenAPI (Swagger)** (Documentação interativa)
- **Arquitetura em Camadas** (Controller, Service, Repository)
- **Data Transfer Objects (DTOs)** para proteção e blindagem dos dados
- **Global Exception Handler** (`@RestControllerAdvice`)
- **CORS Configurado** para consumo via aplicações Front-end/Mobile

## 🚀 Como executar o projeto localmente

### Pré-requisitos
- Java 17 ou superior
- Maven
- PostgreSQL rodando localmente (porta 5432)

### Passos para rodar

1. **Clone o repositório**
   ```bash
   git clone [https://github.com/gothsins/meu-backlog-games.git](https://github.com/gothsins/meu-backlog-games.git)
   cd meu-backlog-games