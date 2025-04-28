DM110 - Trabalho Final

Módulo: Product
Aluno(s): Thiago Ribeiro
Professor: Roberto Rocha
Disciplina: DM110 - Desenvolvimento Java EE
Data de Entrega: 02 de maio de 2025

Resumo do Projeto:

Este projeto implementa o módulo Product utilizando Jakarta EE. Foram desenvolvidos serviços REST para criar, consultar e atualizar produtos, além da implementação de auditoria utilizando EJBs e um MDB para gravação dos registros de alteração. A persistência foi feita com JPA em banco H2 (memória).

Entidades Utilizadas:

ProductEntity:

code (chave primária)
name
description
price
category
AuditEntity:

id (chave primária, auto incremento)
registerCode (código do registro alterado)
operation (tipo de operação: create ou update)
timestamp (data e hora da operação)
Operações Implementadas:

Inserir novo produto
Consultar produto pelo código
Listar todos os produtos
Atualizar produto existente
Registrar auditoria das operações de alteração (create e update)
Banco de Dados:

O projeto utiliza o banco H2 em memória. As tabelas foram criadas utilizando o script SQL create_tables.sql localizado na pasta doc.

Tabelas criadas:

products
audits
Script SQL das Tabelas:

Tabela products:

code: varchar(50) - primary key
name: varchar(255) - not null
description: varchar(500)
price: double
category: varchar(100)
Tabela audits:

id: bigint - primary key auto increment
registerCode: varchar(50) - not null
operation: varchar(20) - not null
timestamp: timestamp - not null
Execução:

Subir o servidor WildFly 27.
Configurar o datasource "java:/jdbc/DM110DS" apontando para o banco H2.
Fazer o deploy da aplicação EAR.
Acessar os endpoints REST usando o Postman.
Endpoints REST:

POST /api/products (criar produto)
GET /api/products/{code} (buscar produto por código)
GET /api/products (listar todos produtos)
PUT /api/products (atualizar produto)
Auditoria:

Todas as operações de criação e atualização de produto geram uma mensagem de auditoria para um MDB, que grava o evento na tabela audits.

Campos auditados:

Código do produto
Tipo da operação (create, update)
Data e hora da operação
Documentação:

Script SQL de criação das tabelas: localizado na pasta /doc/create_tables.sql
Coleção de testes Postman: localizado na pasta /doc/ProductService.postman_collection.json
