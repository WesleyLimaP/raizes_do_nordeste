create table conta_fidelidade(
    id bigint generated always as identity primary key,
    cliente_id bigint not null unique,
    pontos_acumulados bigint,

    constraint fk_conta_fidelidade_cliente
        foreign key (cliente_id)
        references tb_cliente(id)
)