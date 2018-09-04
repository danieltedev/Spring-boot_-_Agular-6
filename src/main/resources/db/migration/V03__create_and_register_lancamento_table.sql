CREATE TABLE lancamento (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(200),
    tipo VARCHAR(50) NOT NULL,
    id_categoria BIGINT(20) NOT NULL,
    id_pessoa BIGINT(20) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
values ('lançamento 1', '2018-06-10', null, 1023.42, 'observação do lançamento 1', 'RECEITA', 2, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
values ('lançamento 2', '2018-06-12', '2018-05-27', 33.42, 'observação do lançamento 1', 'DESPESA', 1, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
values ('lançamento 3', '2018-05-11', '2018-05-28', 42.90, 'observação do lançamento 1', 'RECEITA', 2, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
values ('lançamento 4', '2018-04-03', '2018-04-15', 15.26, 'observação do lançamento 1', 'DESPESA', 3, 4);