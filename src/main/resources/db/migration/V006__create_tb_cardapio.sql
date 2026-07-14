create table tb_cardapio(
    id bigint generated always as identity primary key,
    vigencia_inicio date not null ,
    vigencia_fim date not null,
    unidade_id bigint not null,

    constraint fk_cardapio_unidade
        foreign key (unidade_id)
        references tb_unidade(id)
)