create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(8) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    ciudad varchar(100) not null,
    primary key(id)
);