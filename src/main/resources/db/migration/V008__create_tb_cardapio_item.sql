create table tb_cardapio_item(
    id bigint generated always as identity primary key,
    preco_local float not null,
    disponivel boolean not null ,
    cardapio_id bigint not null ,
    produto_id bigint not null ,

    constraint fk_cardapio_item_cardapio
         foreign key (cardapio_id)
         references tb_cardapio(id),

    constraint fk_cardapio_item_produto
         foreign key (produto_id)
         references tb_produto(id)
)