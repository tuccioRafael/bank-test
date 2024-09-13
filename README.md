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

## Arquitetura em Camadas

A escolha da arquitetura em camadas para esta aplicação foi baseada nas vantagens que ela oferece em termos de organização e manutenção do código. 
A arquitetura em camadas divide a aplicação em diferentes níveis, sendo as principais camadas:

- Camada de Apresentação (Front-End): Responsável pela interação com o usuário, enviando solicitações ao backend e exibindo os dados ao usuário.
- Camada de Aplicação (Serviço): Contém a lógica de negócios da aplicação, ou seja, é onde são implementadas as regras que definem como os dados serão processados.
- Camada de Persistência (Repositório): Lida com a interação com o banco de dados, sendo responsável pelo armazenamento e recuperação dos dados.

### Vantagens da Arquitetura em Camadas:

- Manutenção: Com a separação de responsabilidades, o código se torna mais fácil de manter e entender. Cada camada tem uma função específica, o que facilita a localização de problemas e ajustes.
- Testabilidade: A separação de camadas permite que cada uma seja testada de forma isolada, o que melhora a cobertura de testes e a confiabilidade do sistema.
- Reuso: Componentes de uma camada podem ser reutilizados em diferentes partes da aplicação ou até mesmo em outras aplicações.
- Evolução: Como cada camada tem uma responsabilidade bem definida, é mais fácil fazer evoluções ou melhorias no sistema sem impactar significativamente outras partes da aplicação.
- Escalabilidade: A arquitetura em camadas facilita a divisão do trabalho entre diferentes times ou desenvolvedores, além de permitir a escalabilidade da aplicação em termos de funcionalidade e infraestrutura.

A escolha dessa arquitetura garante que a aplicação seja modular, fácil de manter, testável e capaz de evoluir conforme novas demandas surgem.
