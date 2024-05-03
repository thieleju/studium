-- if needed:
-- DROP SCHEMA dm CASCADE;

CREATE SCHEMA dm;

CREATE TABLE dm.orders (
    order_key     		 serial primary key,
	order_business_key   VARCHAR(16) NOT NULL,
	order_region         VARCHAR(8) NOT NULL
);

CREATE TABLE dm.products (
    product_key   		 serial primary key,
	product_business_key VARCHAR(16) NOT NULL,
    productline			 VARCHAR(24) NOT NULL
);

CREATE TABLE dm.sales (
    sales_key     		 serial primary key,
    quantity     		 NUMERIC	NOT NULL,
    price     	     	 NUMERIC	NOT NULL,
    order_key     		 serial	NOT NULL,
    product_key   		 serial	NOT NULL,
    order_date     		 date 	NOT NULL,
    order_year			 NUMERIC	NOT NULL,
    order_month			 NUMERIC	NOT null
);
alter table dm.sales
add constraint fk_sales_order_key
foreign key(order_key) references dm.orders(order_key);

alter table dm.sales
add constraint fk_sales_product_key
foreign key(product_key) references dm.products(product_key);
