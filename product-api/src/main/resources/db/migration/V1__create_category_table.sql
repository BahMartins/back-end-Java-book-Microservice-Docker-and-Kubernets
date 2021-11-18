create schema if not exist products;

create table products.category (
    id bigserial primary key,
    name varchar(100) not null
);