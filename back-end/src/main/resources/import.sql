INSERT INTO tb_user (name, email, password) VALUES ('Alex', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('João Silva', '(11) 1234-5678', '123.456.789-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Maria Oliveira', '(22) 9876-5432', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Pedro Santos', '(33) 2468-1357', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Ana Souza', '(44) 7531-8642', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Lucas Pereira', '(55) 6423-7198', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Carla Ferreira', '(66) 1852-9743', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Marcos Costa', '(77) 3698-1472', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Patrícia Ramos', '(88) 2587-9631', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Rafael Oliveira', '(99) 1472-3698', '258.369.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Larissa Silva', '(10) 3698-1472', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Gabriel Mendes', '(21) 9874-1236', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Aline Santos', '(32) 7531-6428', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Felipe Rodrigues', '(43) 3698-1472', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Bruna Lima', '(54) 1852-9637', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Rodrigo Almeida', '(65) 2587-9631', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Camila Rocha', '(76) 1472-3698', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Guilherme Costa', '(87) 3698-1472', '258.369.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Fernanda Nunes', '(98) 9874-1236', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Diego Oliveira', '(19) 7531-6428', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Mariana Silva', '(28) 3698-1472', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Thiago Rodrigues', '(37) 1852-9637', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Juliana Almeida', '(46) 2587-9631', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Renato Rocha', '(55) 1472-3698', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Vitória Costa', '(64) 3698-1472', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Carlos Nunes', '(73) 9874-1236', '258.369.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Laura Oliveira', '(82) 7531-6428', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Gustavo Silva', '(91) 3698-1472', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Natália Mendes', '(02) 1852-9637', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Ricardo Lima', '(11) 2587-9631', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Isabela Almeida', '(22) 1472-3698', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Paulo Costa', '(33) 3698-1472', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Larissa Nunes', '(44) 9874-1236', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Marcos Oliveira', '(55) 7531-6428', '258.369.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Tatiane Silva', '(66) 3698-1472', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Fernando Mendes', '(77) 1852-9637', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Amanda Lima', '(88) 2587-9631', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Rafaela Almeida', '(99) 1472-3698', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('José Costa', '(10) 3698-1472', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Camila Nunes', '(21) 9874-1236', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Lucas Oliveira', '(32) 7531-6428', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Ana Clara Silva', '(43) 3698-1472', '258.369.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Pedro Henrique Mendes', '(54) 1852-9637', '369.258.147-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Carolina Lima', '(65) 2587-9631', '987.654.321-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Bruno Almeida', '(76) 1472-3698', '159.357.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Mariana Costa', '(87) 3698-1472', '852.963.741-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Gabriel Nunes', '(98) 9874-1236', '753.951.468-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Tatiana Silva', '(19) 7531-6428', '369.147.258-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('João Victor Oliveira', '(28) 3698-1472', '147.258.369-00');
INSERT INTO tb_cliente (nome, telefone, cpf) VALUES ('Marcela Costa', '(37) 9874-1236', '258.369.147-00');

INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camisa Polo', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Calça Jeans', 79.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Tênis Esportivo', 99.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camiseta Básica', 29.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Shorts de Praia', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Sapato Social', 89.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blusa de Frio', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Vestido Floral', 69.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Jaqueta Jeans', 99.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Bermuda Esportiva', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Saia Midi', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Sandália Rasteira', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camisa Social', 69.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Regata Feminina', 19.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Calça Legging', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blazer Feminino', 89.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Moletom Masculino', 79.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Chinelo de Dedo', 19.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Óculos de Sol', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Casaco de Inverno', 99.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Calça Social', 69.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blusa Cropped', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Shorts Jeans', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Pijama Feminino', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Mochila Escolar', 79.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camiseta Estampada', 29.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Terno Masculino', 149.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Saia Jeans', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Colete Masculino', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Biquíni', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Vestido de Festa', 129.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blusa de Alça', 29.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camisa Xadrez', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Sapatênis', 89.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Vestido Longo', 89.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Cardigan Feminino', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Bota Feminina', 119.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Cinto de Couro', 29.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camiseta Polo', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Calça Cargo', 69.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Chapéu de Praia', 19.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Lenço Estampado', 9.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Calça Legging Esportiva', 59.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Suéter Masculino', 69.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Sapato Casual', 79.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Vestido Midi', 79.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blusa Ciganinha', 49.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Blusa Manga Longa', 39.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Shorts Ciclista', 29.99);
INSERT INTO tb_produto (descricao, valor_unit) VALUES ('Camisola Feminina', 49.99);

INSERT INTO tb_venda (desconto, id_cliente) VALUES (10, 1);

INSERT INTO tb_item_venda (descricao, quantidade, valor_unit, desconto, id_produto, id_venda) VALUES ('Descrição do produto', 5, 40, 10, 1, 1);

INSERT INTO tb_pagamento (parcelas, valor_pago, tipo_pagamento, id_venda) VALUES (12, 100, 1, 1);


