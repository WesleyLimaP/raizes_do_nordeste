create table tb_estoque(
    id bigint generated always as identity primary key,
    quantidade_atual integer not null ,
    quantidade_minima integer not null,
    produto_id bigint not null,
    unidade_id bigint not null,

    constraint fk_estoque_produto
         foreign key (produto_id)
         references tb_produto(id),

    constraint fk_estoque_unidade
         foreign key (unidade_id)
         references tb_unidade(id)
)