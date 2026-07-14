create table tb_campanha_fidelidade(
       id bigint generated always as identity primary key,
       nome varchar(250) not null,
       criterio_segmentacao varchar(250) not null,
       desconto float not null
)