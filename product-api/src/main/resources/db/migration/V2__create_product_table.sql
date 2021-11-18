
create table products.product (
    id bigserial primary key,
    product_identifier varchar not null,
    name varchar(100) not null,
    description varchar not null,
    price decimal not null,
    category_id bigint REFERENCES products.category(id)
);