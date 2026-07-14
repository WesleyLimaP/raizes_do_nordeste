create table tb_item_pedido(
    id bigint generated always as identity primary key,
    pedido_id bigint not null,
    preco_unitario float not null,
    quantidade bigint not null,
    produto_id bigint not null,

    constraint fk_item_pedido_pedido
        foreign key (pedido_id)
        references tb_pedido(id),

    constraint fk_item_pedido_produto
        foreign key (produto_id)
        references tb_produto(id)


)