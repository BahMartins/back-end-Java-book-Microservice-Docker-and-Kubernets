create schema if not exist shopping;

create table shopping.shop (
    id bigserial primary key,
    user_identifier varchar(100) not null,
    date timestamp not null,
    total decimal not null
);

create table shopping.item(
    shop_id bigserial REFERENCES shopping.shop(id),
    product_identifier varchar(100) not null,
    price decimal not null
);