INSERT INTO TB_CATEGORIA(NOME) VALUES('Informática');
INSERT INTO TB_CATEGORIA(NOME) VALUES('Escritório');

INSERT INTO TB_PRODUTO(NOME, PRECO) VALUES('Computador', 2000.00);
INSERT INTO TB_PRODUTO(NOME, PRECO) VALUES('Impressora', 800.00);
INSERT INTO TB_PRODUTO(NOME, PRECO) VALUES('Mouse', 80.00);

INSERT INTO TB_PRODUTO_CATEGORIA(PRODUTO_ID, CATEGORIA_ID) VALUES(1, 1);
INSERT INTO TB_PRODUTO_CATEGORIA(PRODUTO_ID, CATEGORIA_ID) VALUES(2, 1);
INSERT INTO TB_PRODUTO_CATEGORIA(PRODUTO_ID, CATEGORIA_ID) VALUES(2, 2);
INSERT INTO TB_PRODUTO_CATEGORIA(PRODUTO_ID, CATEGORIA_ID) VALUES(3, 1);

INSERT INTO TB_ESTADO(NOME) VALUES ('Minas Gerais');
INSERT INTO TB_ESTADO(NOME) VALUES ('São Paulo');

INSERT INTO TB_CIDADE(NOME, ESTADO_ID) VALUES ('Uberlândia', 1);
INSERT INTO TB_CIDADE(NOME, ESTADO_ID) VALUES ('São Paulo', 2);
INSERT INTO TB_CIDADE(NOME, ESTADO_ID) VALUES ('Campinas', 2);

INSERT INTO TB_CLIENTE(NOME, EMAIL, CPF_OU_CNPJ, TIPO_CLIENTE) VALUES ('Maria Silva', 'maria@gmail.com', '3678912377', 1);
INSERT INTO TB_CLIENTE(NOME, EMAIL, CPF_OU_CNPJ, TIPO_CLIENTE) VALUES ('Maria Silva', 'maria@gmail.com', '3678912377', 1);

INSERT INTO TB_TELEFONE(TELEFONES, CLIENTE_ID) VALUES('15975328', 1);
INSERT INTO TB_TELEFONE(TELEFONES, CLIENTE_ID) VALUES('75315982', 1);

INSERT INTO TB_ENDERECO(BAIRRO, CEP, COMPLEMENTO, LOGRADOURO, NUMERO, CIDADE_ID, CLIENTE_ID) VALUES('Jardim', '38022834', 'Apto 203', 'Rua Flores', '300', 1, 1);
INSERT INTO TB_ENDERECO(BAIRRO, CEP, COMPLEMENTO, LOGRADOURO, NUMERO, CIDADE_ID, CLIENTE_ID) VALUES('Centro', '38077012', 'Sala 800', 'Avenida Matos', '105', 2, 1);

INSERT INTO TB_PEDIDO(INSTANTE, ENDERECO_DE_ENTREGA_ID, CLIENTE_ID) VALUES(TIMESTAMP WITH TIME ZONE '2017-07-30T10:32:07.12345Z', 1, 1);
INSERT INTO TB_PEDIDO(INSTANTE, ENDERECO_DE_ENTREGA_ID, CLIENTE_ID) VALUES(TIMESTAMP WITH TIME ZONE '2017-10-10T19:35:07.12345Z', 2, 1);

INSERT INTO TB_PAGAMENTO(ESTADO, PEDIDO_ID) VALUES(2, 1);
INSERT INTO TB_PAGAMENTO_COM_CARTAO(PEDIDO_ID, NUMERO_DE_PARCELAS) VALUES(1, 6);

INSERT INTO TB_PAGAMENTO(ESTADO, PEDIDO_ID) VALUES(1, 2);
INSERT INTO TB_PAGAMENTO_COM_BOLETO(PEDIDO_ID, DATA_VENCIMENTO, DATA_PAGAMENTO) VALUES(2, TIMESTAMP WITH TIME ZONE '2017-10-20T19:35:07.12345Z', NULL);














