-- Criação da tabela de Produtos
CREATE TABLE products (
    code VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    price DOUBLE,
    category VARCHAR(100)
);

-- Criação da tabela de Auditoria
CREATE TABLE audits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registerCode VARCHAR(50) NOT NULL,
    operation VARCHAR(20) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);
