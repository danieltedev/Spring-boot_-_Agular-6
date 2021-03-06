CREATE TABLE usuario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL
);

CREATE TABLE permissao (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE usuario_permissao (
    id_usuario BIGINT(20) NOT NULL,
    id_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (id_usuario, id_permissao),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

INSERT INTO usuario (nome, email, senha) values ('Administrador', 'administrador@dtel.com', '{bcrypt}$2a$10$hL7O6oBf2LcXWj2dvcXu/ep2Kxx85hcKxNy3gvcoSfd/WrZyQyiRm');
INSERT INTO usuario (nome, email, senha) values ('Antonio Carlos', 'antonio.carlos@dtel.com', '{bcrypt}$2a$10$hL7O6oBf2LcXWj2dvcXu/ep2Kxx85hcKxNy3gvcoSfd/WrZyQyiRm');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_PESSOA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_LANCAMENTO');

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