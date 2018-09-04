create table pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NUll
);

INSERT INTO pessoa (nome, ativo) VALUES ('Nome 1', true);
INSERT INTO pessoa (nome, ativo) VALUES ('Nome 2', true);
INSERT INTO pessoa (nome, ativo) VALUES ('Nome 3', true);
INSERT INTO pessoa (nome, ativo) VALUES ('Nome 4', true);