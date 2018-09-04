CREATE TABLE usuario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL
);

CREATE TABLE permissao (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE usuario_permissao (
    id_usuario BIGINT(20) NOT NULL,
    id_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (id_usuario, id_permissao),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

INSERT INTO usuario (id, nome, email, senha) values (1, 'Administrador', 'administrador@dtel.com', '{bcrypt}$2a$10$X607ZPhQ4EgGNaYKt3n4SQNjIv9zc.VMWEuha7oLAL5IvcL5.');
INSERT INTO usuario (id, nome, email, senha) values (1, 'Antonio Carlos', 'antonio.carlos@dtel.com', '{bcrypt}$2a$10$X607ZPhQ4EgGNaYKt3n4SQNjIv9zc.VMWEuha7oLAL5IvcL5.');

INSERT INTO permissao (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (4, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (5, 'ROLE_REMOVER_PESSOA');

INSERT INTO permissao (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (7, 'ROLE_PESQUISAR_LANCAMENTO');
INSERT INTO permissao (8, 'ROLE_REMOVER_LANCAMENTO');

INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);

INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 7);