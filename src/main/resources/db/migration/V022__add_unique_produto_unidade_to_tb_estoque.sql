ALTER TABLE tb_estoque
    ADD CONSTRAINT uk_estoque_produto_unidade UNIQUE (produto_id, unidade_id);
