create table tb_pagamento(
    id bigint generated always as identity primary key,
    pedido_id bigint not null,
    status varchar(250) not null,
    getway_externo varchar(250) not null,
    referencial_externa varchar,
    data_hora_atualizacao date,

    constraint fk_pagamento_pedido
        foreign key (pedido_id)
        references tb_pedido(id)



)