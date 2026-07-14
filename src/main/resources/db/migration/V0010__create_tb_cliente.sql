create table tb_cliente(
    id bigint generated always as identity primary key,
    nome varchar(250) not null,
    cpf varchar(250) not null,
    email varchar(250) not null,
    data_nascimento date not null


)