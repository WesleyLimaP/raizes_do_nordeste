alter table tb_item_pedido
    drop constraint fk_item_pedido_pedido;

alter table tb_item_pedido
    add constraint fk_item_pedido_pedido
        foreign key (pedido_id)
        references tb_pedido(id)
        on delete cascade;
