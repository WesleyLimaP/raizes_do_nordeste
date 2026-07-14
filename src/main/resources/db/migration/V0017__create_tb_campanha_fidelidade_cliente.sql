create table tb_campanha_fidelidade_cliente(
       camapanha_fidelidade_id bigint not null,
       cliente_id bigint not null,

       primary key (camapanha_fidelidade_id, cliente_id),

       constraint fk_campanha_fidelidade_cliente_campanha_fidelidade
       foreign key (camapanha_fidelidade_id)
       references tb_campanha_fidelidade(id),

       constraint fk_campanha_fidelidade_cliente_cliente
       foreign key (cliente_id)
       references tb_cliente(id)

)