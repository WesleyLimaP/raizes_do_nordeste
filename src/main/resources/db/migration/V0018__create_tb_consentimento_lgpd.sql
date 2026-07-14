create table tb_consentimento_lgpd(
    id bigint generated always as identity primary key,
    cliente_id bigint not null,
    tipo_consentimento varchar(250) not null,
    revogado boolean not null,
    data_consentimento date not null,

    constraint fk_consentimento_lgpd_cliente
        foreign key (cliente_id)
        references tb_cliente(id)



)