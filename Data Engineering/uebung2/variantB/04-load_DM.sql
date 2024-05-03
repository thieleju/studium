delete from dm.orders;
insert into dm.orders(order_business_key,order_region) 
	SELECT order_business_key, order_region FROM dwh.hub_order, dwh.sat_order_data
		WHERE hub_order.order_hash_key = sat_order_data.order_hash_key;
	
delete from dm.products;
insert into dm.products(product_business_key, productline) 
	SELECT product_business_key, productline FROM dwh.hub_product, dwh.sat_product_data
		WHERE hub_product.product_hash_key = sat_product_data.product_hash_key;
	
delete from dm.sales ;
insert into dm.sales (quantity, price, order_key, product_key, order_date, order_year, order_month)
select sod.quantityordered , sod.priceeach, 
o.order_key, p.product_key, sod2.order_date, extract (year from order_date), extract (month from order_date)
from dwh.link_orderline lo, dwh.sat_orderline_data sod, dwh.hub_order ho, dwh.hub_product hp, dwh.sat_order_data sod2, 
dm.orders o, dm.products p
where sod.orderline_hash_key = lo.orderline_hash_key
and lo.order_hash_key  = ho.order_hash_key 
and lo.product_hash_key = hp.product_hash_key
and ho.order_hash_key = sod2.order_hash_key 
and o.order_business_key =  ho.order_business_key
and p.product_business_key  =  hp.product_business_key  
;
--select count(*) from dm.sales  ;