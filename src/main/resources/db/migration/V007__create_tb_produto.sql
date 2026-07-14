create table tb_produto(
    id bigint generated always as identity primary key ,
    nome varchar(250) not null,
    descricao varchar(250),
    preco_base float not null,
    categoria varchar(250),
    disponibilidade_sazonal boolean

)