create table tb_movimento_pontos(
    id bigint generated always as identity primary key,
    conta_fidelidade_id bigint not null,
    tipo varchar(250) not null,
    data_hora date not null,
    quantidade bigint not null,
    origem varchar(250) not null,

    constraint fk_movimento_pontos_conta_fidelidade
        foreign key (conta_fidelidade_id)
        references conta_fidelidade(id)
)