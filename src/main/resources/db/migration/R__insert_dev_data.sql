-- Script de dados de desenvolvimento
-- Este arquivo é executado após cada migração Flyway
-- Para limpar e reinserir dados a cada reinicialização, configure spring.flyway.clean-disabled=false em dev

-- Limpar dados existentes (em ordem reversa das dependências)
DELETE FROM tb_pagamento;
DELETE FROM tb_consentimento_lgpd;
DELETE FROM tb_campanha_fidelidade_cliente;
DELETE FROM tb_movimento_pontos;
DELETE FROM tb_conta_fidelidade;
DELETE FROM tb_pedido;
DELETE FROM tb_cliente;
DELETE FROM tb_estoque;
DELETE FROM tb_cardapio_item;
DELETE FROM tb_cardapio;
DELETE FROM tb_produto;
DELETE FROM tb_auditoria;
DELETE FROM tb_usuario_papel;
DELETE FROM tb_usuario;
DELETE FROM tb_papel;
DELETE FROM tb_unidade;

-- Inserir dados de desenvolvimento

-- Unidades
INSERT INTO tb_unidade (nome, endereco, regiao, tipo_cozinha) VALUES
('Unidade Centro', 'Rua Central, 100', 'Centro', 'Nordestina'),
('Unidade Norte', 'Av. Norte, 500', 'Norte', 'Baiana'),
('Unidade Sul', 'Rua do Sul, 200', 'Sul', 'Mineira');

-- Papéis
INSERT INTO tb_papel (descricao) VALUES
('ADMIN'),
('GERENTE'),
('ATENDENTE'),
('CLIENTE');

-- Usuários
INSERT INTO tb_usuario (nome, login, senhahash, unidade_id) VALUES
('Administrador', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('Gerente Centro', 'gerente1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('Atendente Centro', 'atendente1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('Gerente Norte', 'gerente2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte'));

-- Usuário-Papel (associações)
INSERT INTO tb_usuario_papel (usuario_id, papel_id) VALUES
((SELECT id FROM tb_usuario WHERE login = 'admin'), (SELECT id FROM tb_papel WHERE descricao = 'ADMIN')),
((SELECT id FROM tb_usuario WHERE login = 'gerente1'), (SELECT id FROM tb_papel WHERE descricao = 'GERENTE')),
((SELECT id FROM tb_usuario WHERE login = 'atendente1'), (SELECT id FROM tb_papel WHERE descricao = 'ATENDENTE')),
((SELECT id FROM tb_usuario WHERE login = 'gerente2'), (SELECT id FROM tb_papel WHERE descricao = 'GERENTE'));

-- Produtos
INSERT INTO tb_produto (nome, descricao, preco_base, categoria) VALUES
('Baião de Dois', 'Arroz com feijão de corda e queijo coalho', 25.90, 'Pratos Típicos'),
('Carne de Sol', 'Carne de sol com macaxeira e paçoca', 32.50, 'Pratos Típicos'),
('Sarapatel', 'Prato tradicional nordestino', 28.00, 'Pratos Típicos'),
('Acarajé', 'Feijão fradinho frito com vatapá e camarão', 18.90, 'Pratos Típicos'),
('Cuscuz', 'Cuscuz de milho com manteiga e queijo', 15.50, 'Pratos Típicos'),
('Tapioca', 'Tapioca recheada com queijo e coco', 12.00, 'Lanches'),
('Coco Verde', 'Água de coco verde natural', 8.00, 'Bebidas'),
('Suco de Caju', 'Suco natural de caju', 10.00, 'Bebidas'),
('Bolo de Rolo', 'Bolo de rolo com goiabada', 9.50, 'Sobremesas'),
('Cartola', 'Banana frita com queijo e canela', 11.00, 'Sobremesas');

-- Cardápios
INSERT INTO tb_cardapio (vigencia_inicio, vigencia_fim, unidade_id) VALUES
('2026-01-01', '2026-12-31', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('2026-01-01', '2026-12-31', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
('2026-01-01', '2026-12-31', (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul'));

-- Itens do Cardápio
INSERT INTO tb_cardapio_item (preco_local, disponivel, cardapio_id, produto_id) VALUES
(25.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois')),
(32.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol')),
(28.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Sarapatel')),
(18.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Acarajé')),
(15.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Cuscuz')),
(12.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Tapioca')),
(8.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Coco Verde')),
(10.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Suco de Caju')),
(9.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Bolo de Rolo')),
(11.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Cartola')),
(26.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois')),
(33.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol')),
(29.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Sarapatel')),
(19.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Acarajé')),
(16.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Cuscuz')),
(27.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois')),
(34.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol')),
(30.00, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Sarapatel')),
(20.90, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Acarajé')),
(17.50, true, (SELECT id FROM tb_cardapio WHERE unidade_id = (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul') LIMIT 1), (SELECT id FROM tb_produto WHERE nome = 'Cuscuz'));

-- Estoque
INSERT INTO tb_estoque (quantidade_atual, quantidade_minima, produto_id, unidade_id) VALUES
(50, 10, (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(30, 5, (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(40, 8, (SELECT id FROM tb_produto WHERE nome = 'Sarapatel'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(60, 15, (SELECT id FROM tb_produto WHERE nome = 'Acarajé'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(45, 10, (SELECT id FROM tb_produto WHERE nome = 'Cuscuz'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(100, 20, (SELECT id FROM tb_produto WHERE nome = 'Tapioca'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(80, 15, (SELECT id FROM tb_produto WHERE nome = 'Coco Verde'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(90, 20, (SELECT id FROM tb_produto WHERE nome = 'Suco de Caju'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(35, 10, (SELECT id FROM tb_produto WHERE nome = 'Bolo de Rolo'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(40, 10, (SELECT id FROM tb_produto WHERE nome = 'Cartola'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
(45, 10, (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
(25, 5, (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
(35, 8, (SELECT id FROM tb_produto WHERE nome = 'Sarapatel'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
(55, 15, (SELECT id FROM tb_produto WHERE nome = 'Acarajé'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
(40, 10, (SELECT id FROM tb_produto WHERE nome = 'Cuscuz'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
(40, 10, (SELECT id FROM tb_produto WHERE nome = 'Baião de Dois'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul')),
(20, 5, (SELECT id FROM tb_produto WHERE nome = 'Carne de Sol'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul')),
(30, 8, (SELECT id FROM tb_produto WHERE nome = 'Sarapatel'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul')),
(50, 15, (SELECT id FROM tb_produto WHERE nome = 'Acarajé'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul')),
(35, 10, (SELECT id FROM tb_produto WHERE nome = 'Cuscuz'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul'));

-- Clientes
INSERT INTO tb_cliente (nome, cpf, email, data_nascimento) VALUES
('João Silva', '123.456.789-00', 'joao.silva@email.com', '1990-05-15'),
('Maria Santos', '987.654.321-11', 'maria.santos@email.com', '1985-10-20'),
('Pedro Oliveira', '456.789.123-22', 'pedro.oliveira@email.com', '1995-03-08'),
('Ana Costa', '789.123.456-33', 'ana.costa@email.com', '1988-12-25'),
('Carlos Lima', '321.654.987-44', 'carlos.lima@email.com', '1992-07-30');

-- Contas de Fidelidade
INSERT INTO tb_conta_fidelidade (cliente_id, pontos_acumulados) VALUES
((SELECT id FROM tb_cliente WHERE nome = 'João Silva'), 150),
((SELECT id FROM tb_cliente WHERE nome = 'Maria Santos'), 320),
((SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira'), 85),
((SELECT id FROM tb_cliente WHERE nome = 'Ana Costa'), 210),
((SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima'), 95);

-- Movimentos de Pontos
INSERT INTO tb_movimento_pontos (conta_fidelidade_id, tipo, data_hora, quantidade, origem) VALUES
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'João Silva')), 'CREDITO', '2026-07-01', 50, 'COMPRA'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'João Silva')), 'CREDITO', '2026-07-10', 100, 'BÔNUS'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Maria Santos')), 'CREDITO', '2026-06-15', 200, 'COMPRA'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Maria Santos')), 'CREDITO', '2026-07-05', 120, 'BÔNUS'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira')), 'CREDITO', '2026-07-12', 85, 'COMPRA'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Ana Costa')), 'CREDITO', '2026-06-20', 150, 'COMPRA'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Ana Costa')), 'CREDITO', '2026-07-08', 60, 'BÔNUS'),
((SELECT id FROM tb_conta_fidelidade WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima')), 'CREDITO', '2026-07-11', 95, 'COMPRA');

-- Campanhas de Fidelidade
INSERT INTO tb_campanha_fidelidade (nome, criterio_segmentacao, desconto) VALUES
('Dobro de Pontos', 'Todos os clientes', 0.10),
('Cliente VIP', 'Mais de 200 pontos', 0.15),
('Primeira Compra', 'Novos clientes', 0.20);

-- Campanha Fidelidade Cliente
INSERT INTO tb_campanha_fidelidade_cliente (camapanha_fidelidade_id, cliente_id) VALUES
((SELECT id FROM tb_campanha_fidelidade WHERE nome = 'Cliente VIP' LIMIT 1), (SELECT id FROM tb_cliente WHERE nome = 'Maria Santos' LIMIT 1)),
((SELECT id FROM tb_campanha_fidelidade WHERE nome = 'Cliente VIP' LIMIT 1), (SELECT id FROM tb_cliente WHERE nome = 'Ana Costa' LIMIT 1));

-- Consentimentos LGPD
INSERT INTO tb_consentimento_lgpd (cliente_id, tipo_consentimento, revogado, data_consentimento) VALUES
((SELECT id FROM tb_cliente WHERE nome = 'João Silva'), 'MARKETING', false, '2026-01-15'),
((SELECT id FROM tb_cliente WHERE nome = 'João Silva'), 'DADOS_PESSOAIS', false, '2026-01-15'),
((SELECT id FROM tb_cliente WHERE nome = 'Maria Santos'), 'MARKETING', true, '2026-02-01'),
((SELECT id FROM tb_cliente WHERE nome = 'Maria Santos'), 'DADOS_PESSOAIS', false, '2026-02-01'),
((SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira'), 'MARKETING', false, '2026-03-10'),
((SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira'), 'DADOS_PESSOAIS', false, '2026-03-10'),
((SELECT id FROM tb_cliente WHERE nome = 'Ana Costa'), 'MARKETING', false, '2026-04-05'),
((SELECT id FROM tb_cliente WHERE nome = 'Ana Costa'), 'DADOS_PESSOAIS', false, '2026-04-05'),
((SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima'), 'MARKETING', false, '2026-05-20'),
((SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima'), 'DADOS_PESSOAIS', false, '2026-05-20');

-- Pedidos
INSERT INTO tb_pedido (canal, status, data_hora, cliente_id, unidade_id) VALUES
('APP', 'ENTREGUE', '2026-07-01', (SELECT id FROM tb_cliente WHERE nome = 'João Silva'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('TOTEM', 'ENTREGUE', '2026-07-05', (SELECT id FROM tb_cliente WHERE nome = 'Maria Santos'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('BALCAO', 'PRONTO', '2026-07-10', (SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Norte')),
('APP', 'CONFIRMADO', '2026-07-12', (SELECT id FROM tb_cliente WHERE nome = 'Ana Costa'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Centro')),
('PICKUP', 'EM_PREPARO', '2026-07-13', (SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima'), (SELECT id FROM tb_unidade WHERE nome = 'Unidade Sul'));

-- Pagamentos
INSERT INTO tb_pagamento (pedido_id, status, getway_externo, referencial_externa, data_hora_atualizacao) VALUES
((SELECT id FROM tb_pedido WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'João Silva') LIMIT 1), 'APROVADO', 'MERCADO_PAGO', 'MP-001', '2026-07-01'),
((SELECT id FROM tb_pedido WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Maria Santos') LIMIT 1), 'APROVADO', 'PIX', 'PIX-002', '2026-07-05'),
((SELECT id FROM tb_pedido WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Pedro Oliveira') LIMIT 1), 'APROVADO', 'CREDITO', 'CARD-003', '2026-07-10'),
((SELECT id FROM tb_pedido WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Ana Costa') LIMIT 1), 'PENDENTE', 'PIX', NULL, '2026-07-12'),
((SELECT id FROM tb_pedido WHERE cliente_id = (SELECT id FROM tb_cliente WHERE nome = 'Carlos Lima') LIMIT 1), 'PENDENTE', 'MERCADO_PAGO', NULL, '2026-07-13');

-- Auditoria
INSERT INTO tb_auditoria (acao, entidade_afetada, data_hora, detalhes, usuario_id) VALUES
('INSERT', 'tb_cliente', '2026-07-01', 'Novo cliente cadastrado: João Silva', (SELECT id FROM tb_usuario WHERE login = 'admin')),
('INSERT', 'tb_pedido', '2026-07-01', 'Novo pedido criado: 1', (SELECT id FROM tb_usuario WHERE login = 'atendente1')),
('UPDATE', 'tb_pedido', '2026-07-01', 'Status alterado para ENTREGUE', (SELECT id FROM tb_usuario WHERE login = 'atendente1')),
('INSERT', 'tb_cliente', '2026-07-02', 'Novo cliente cadastrado: Maria Santos', (SELECT id FROM tb_usuario WHERE login = 'admin')),
('INSERT', 'tb_pedido', '2026-07-05', 'Novo pedido criado: 2', (SELECT id FROM tb_usuario WHERE login = 'atendente1')),
('UPDATE', 'tb_pedido', '2026-07-05', 'Status alterado para ENTREGUE', (SELECT id FROM tb_usuario WHERE login = 'atendente1'));
