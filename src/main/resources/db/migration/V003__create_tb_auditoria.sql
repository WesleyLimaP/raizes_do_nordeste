create table tb_auditoria (
    id bigint generated always as identity primary key,
    acao varchar(250) not null,
    entidade_afetada varchar(250) not null ,
    data_hora date not null ,
    detalhes varchar(250),
    usuario_id bigint not null,

    constraint fk_auditoria_usuario
        foreign key (usuario_id)
        references tb_usuario(id)

)