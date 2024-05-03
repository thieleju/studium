-- if needed:
-- DROP SCHEMA dwh CASCADE;

CREATE SCHEMA dwh;

CREATE TABLE dwh.hub_product (
    product_hash_key     BYTEA       NOT NULL,
	product_business_key VARCHAR(16) NOT NULL,
	load_date            DATE        NOT NULL,
    record_source        VARCHAR(8)  NOT NULL
);

CREATE TABLE dwh.hub_order (
    order_hash_key       BYTEA       NOT NULL,
	order_business_key   VARCHAR(16) NOT NULL,
	load_date            DATE        NOT NULL,
    record_source        VARCHAR(8)  NOT NULL
);

CREATE TABLE dwh.link_orderline (
	orderline_hash_key   BYTEA       NOT NULL,
	product_hash_key     BYTEA       NOT NULL,
	order_hash_key       BYTEA       NOT NULL,
	load_date            DATE        NOT NULL,
    record_source        VARCHAR(8)  NOT NULL
);

alter table dwh.hub_product 
add constraint pk_hub_product
primary key(product_hash_key);

alter table dwh.hub_order 
add constraint pk_hub_order 
primary key(order_hash_key);

alter table dwh.link_orderline 
add constraint pk_link_orderline
primary key(orderline_hash_key);

alter table dwh.link_orderline
add constraint fk_link_orderline_product
foreign key(product_hash_key) references dwh.hub_product(product_hash_key);

alter table dwh.link_orderline
add constraint fk_link_orderline_order
foreign key(order_hash_key) references dwh.hub_order(order_hash_key);

CREATE TABLE dwh.sat_product_data (
    product_hash_key     BYTEA       NOT NULL,
	load_date            DATE        NOT NULL,
    record_source        VARCHAR(8)  NOT NULL,
	productline			 VARCHAR(24) NOT NULL
);

CREATE TABLE dwh.sat_order_data (
    order_hash_key       BYTEA      NOT NULL,
	load_date            DATE       NOT NULL,
    record_source        VARCHAR(8) NOT NULL,
	order_date           DATE       NOT NULL,
	order_region         VARCHAR(8) NOT NULL
);

CREATE TABLE dwh.sat_orderline_data (
	orderline_hash_key   BYTEA       NOT NULL,
	load_date            DATE        NOT NULL,
    record_source        VARCHAR(8)  NOT NULL,
	orderlinenumber      VARCHAR(16) NOT NULL,
	quantityordered      NUMERIC     NOT NULL,
	priceeach            NUMERIC     NOT NULL
);

alter table dwh.sat_product_data 
add constraint pk_sat_product_data
primary key(product_hash_key, load_date);

alter table dwh.sat_order_data 
add constraint pk_sat_order_data
primary key(order_hash_key, load_date);

alter table dwh.sat_orderline_data 
add constraint pk_sat_orderline_data
primary key(orderline_hash_key, load_date);

alter table dwh.sat_product_data
add constraint fk_sat_product_data
foreign key(product_hash_key) references dwh.hub_product(product_hash_key);

alter table dwh.sat_order_data
add constraint fk_sat_order_data
foreign key(order_hash_key) references dwh.hub_order(order_hash_key);

alter table dwh.sat_orderline_data
add constraint fk_sat_orderline_data
foreign key(orderline_hash_key) references dwh.link_orderline(orderline_hash_key);