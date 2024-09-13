# Transferências Financeiras - Aplicação Java

Esta é uma aplicação de transferências financeiras construída em Java usando Spring Boot para o backend e Vue.js para o frontend. Ela permite que usuários realizem transferências entre contas bancárias, com regras de validação e cálculo de taxas baseadas em datas de transferência.

## Funcionalidades

- Cadastro de transferências bancárias entre contas de origem e destino.
- Validação de campos de entrada, incluindo contas de 10 dígitos e valores monetários.
- Cálculo de taxas de transferência com base na data de solicitação e na data da transferência.
- Exibição de detalhes de transferências.
- Edição e exclusão de transferências cadastradas.

## Tecnologias Utilizadas

- **Backend**: Spring Boot, Java
- **Frontend**: Vue.js 3, TypeScript
- **Banco de Dados**: H2 (pode ser alterado para outro banco de dados relacional como MySQL, PostgreSQL, etc.)

## Pré-requisitos

Antes de iniciar, você precisará ter o seguinte instalado:

- [JDK 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/) (para o frontend)
- [Frontend](https://github.com/tuccioRafael/bank-test-front) (aplicação do front)

## Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/sua-aplicacao.git
cd sua-aplicacao
```

## Endpoints
```
    /transfers [GET]
    /transfers [POST]
    /transfers [PUT]
    /transfers/:id [DELETE]
    /transfers/:id [GET]
```