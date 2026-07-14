create table tb_usuario (
    id bigint generated always as identity primary key not null,
    nome varchar (255) not null,
    login varchar (255) not null,
    senhahash varchar (255) not null,
    unidadeId bigint,

    constraint fk_usuario_unidade
        foreign key (unidadeId)
        references tb_unidade(id)
)