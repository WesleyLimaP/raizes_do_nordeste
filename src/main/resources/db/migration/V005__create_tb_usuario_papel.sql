create table tb_usuario_papel (
    usuario_id bigint,
    papel_id bigint,

    primary key (usuario_id, papel_id),

    constraint fk_usuario_papel
        foreign key (usuario_id)
        references tb_usuario(id)
        on delete cascade,

    constraint fk_papel_usuario
        foreign key (papel_id)
        references tb_papel(id)
        on delete cascade
)