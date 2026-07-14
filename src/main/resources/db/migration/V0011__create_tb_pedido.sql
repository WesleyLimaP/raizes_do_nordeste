create table tb_pedido(
    id bigint generated always as identity primary key,
    canal varchar(250) not null,
    status varchar(250) not null,
    data_hora date not null,
    cliente_id bigint not null,
    unidade_id bigint not null,

    constraint fk_pedido_cliente
        foreign key (cliente_id)
        references tb_cliente(id),

    constraint fk_pedido_unidade
        foreign key (unidade_id)
        references tb_unidade(id)


)