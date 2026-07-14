create table tb_unidade (
    id  bigint generated always as identity primary key not null ,
    nome varchar(200) not null,
    endereco varchar(250) not null,
    regiao varchar(250) not null,
    tipo_cozinha varchar(250) not null
)