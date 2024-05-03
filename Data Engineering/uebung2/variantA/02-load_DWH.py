SRC_FILE = "./sales_data_sample.csv"
SRC_FILE_DELIM       = ','
SRC_FILE_COL_ORDER_KEY   = "ORDERNUMBER"
SRC_FILE_COL_ORDER_DATE   = "ORDERDATE"
SRC_FILE_COL_ORDER_REGION   = "TERRITORY"
SRC_FILE_COL_PRODUCT_KEY = "PRODUCTCODE"
SRC_FILE_COL_PRODUCT_LINE = "PRODUCTLINE"
SRC_FILE_COL_ORDERLINE_KEY = "ORDERLINENUMBER"
SRC_FILE_COL_ORDERLINE_STATUS = "STATUS"
SRC_FILE_COL_ORDERLINE_QUANTITY = "QUANTITYORDERED"
SRC_FILE_COL_ORDERLINE_PRICE_EACH = "PRICEEACH"

import os
import sys
def check_file_existence(a_path):
    if not os.path.isfile(a_path):
        print("File {} does not exist.".format(a_path))
        sys.exit()

check_file_existence(SRC_FILE)

import csv
import hashlib
product_dict = {}
order_dict = {}
orderline_dict = {}
with open(SRC_FILE, encoding="cp1252") as f:
        myreader = csv.DictReader(f, delimiter=SRC_FILE_DELIM)
        for line in myreader:
             current_product_key   = line[SRC_FILE_COL_PRODUCT_KEY].strip()
             current_productline   = line[SRC_FILE_COL_PRODUCT_LINE].strip()
             current_order_key     = line[SRC_FILE_COL_ORDER_KEY].strip() 
             datestr_token_list = (line[SRC_FILE_COL_ORDER_DATE]).strip().split(sep = '/') 

             datestr_year = datestr_token_list[2].split(sep = ' ')[0]
             datestr_month = datestr_token_list[0]
             if len(datestr_month) == 1 :
                  datestr_month = '0' + datestr_month
             datestr_day = datestr_token_list[1]
             if len(datestr_day) == 1 :
                  datestr_day = '0' + datestr_day
             current_order_date = datestr_year + "-" + datestr_month + "-" + datestr_day

             current_order_region  = line[SRC_FILE_COL_ORDER_REGION].strip() 
             current_orderline_key = current_order_key + "__" + line[SRC_FILE_COL_ORDERLINE_KEY].strip()
             current_orderline_status = line[SRC_FILE_COL_ORDERLINE_STATUS].strip()
             current_orderline_quantity = line[SRC_FILE_COL_ORDERLINE_QUANTITY].strip()
             current_orderline_priceeach = line[SRC_FILE_COL_ORDERLINE_PRICE_EACH].strip()

             if current_orderline_status == "Shipped" :
                current_product_hashkey = hashlib.md5(current_product_key.encode()).hexdigest()
                product_dict[current_product_key] = {"line" : current_productline
                                                     , "hashkey" : current_product_hashkey}
                current_order_hashkey = hashlib.md5(current_order_key.encode()).hexdigest()
                order_dict[current_order_key] = {"date" : current_order_date, "region" : current_order_region
                                                 , "hashkey" : current_order_hashkey}
                orderline_dict[current_orderline_key] = {"quantity" : current_orderline_quantity
                                                         , "priceeach" : current_orderline_priceeach
                                                         , "product_hashkey": current_product_hashkey
                                                         , "order_hashkey" : current_order_hashkey
                                                         , "hashkey" : hashlib.md5((current_order_key+current_product_key).encode()).hexdigest()}

print("Read products: " + str(len(product_dict)))
print("Read orders: " + str(len(order_dict)))
print("Read orderlines: " + str(len(orderline_dict)))


import psycopg2 as db
from datetime import date
today = str(date.today())
conn_string = "dbname='vehicle_model_vendor' host='localhost' user='postgres' password='postgres'"
conn = db.connect(conn_string)
cur = conn.cursor()
cur.execute("delete from dwh.sat_orderline_data;")
cur.execute("delete from dwh.sat_order_data;")
cur.execute("delete from dwh.sat_product_data;")
cur.execute("delete from dwh.link_orderline;")
cur.execute("delete from dwh.hub_product;")
cur.execute("delete from dwh.hub_order;")

insert_stmnt_hub_products = "insert into dwh.hub_product (product_hash_key, product_business_key, load_date, record_source) values (%s,%s,%s,%s)"
insert_stmnt_sat_products = "insert into dwh.sat_product_data (product_hash_key, load_date, record_source, productline) values (%s,%s,%s,%s)"
hub_product_list_for_db_insert = []
sat_product_list_for_db_insert = []
for product_key, product_item in product_dict.items() :
    hub_product_list_for_db_insert.append((product_item["hashkey"], product_key, today, "op_s_1"))
    sat_product_list_for_db_insert.append((product_item["hashkey"], today, "op_s_1", product_item["line"]))
cur.executemany(insert_stmnt_hub_products, hub_product_list_for_db_insert)
cur.executemany(insert_stmnt_sat_products, sat_product_list_for_db_insert)

insert_stmnt_hub_orders = "insert into dwh.hub_order (order_hash_key, order_business_key, load_date, record_source) values (%s,%s,%s,%s)"
insert_stmnt_sat_orders = "insert into dwh.sat_order_data (order_hash_key, load_date, record_source, order_date, order_region) values (%s,%s,%s,%s,%s)"
hub_order_list_for_db_insert = []
sat_order_list_for_db_insert = []
for order_key, order_item in order_dict.items() :
    hub_order_list_for_db_insert.append((order_item["hashkey"], order_key, today, "op_s_1"))
    sat_order_list_for_db_insert.append((order_item["hashkey"], today, "op_s_1", order_item["date"], order_item["region"]))
cur.executemany(insert_stmnt_hub_orders, hub_order_list_for_db_insert)
cur.executemany(insert_stmnt_sat_orders, sat_order_list_for_db_insert)

insert_stmnt_hub_orderlines = "insert into dwh.link_orderline (orderline_hash_key, product_hash_key, order_hash_key, load_date, record_source) values (%s,%s,%s,%s,%s)"
insert_stmnt_sat_orderlines = "insert into dwh.sat_orderline_data (orderline_hash_key, load_date, record_source, orderlinenumber, quantityordered,priceeach) values (%s,%s,%s,%s,%s,%s)"
hub_orderline_list_for_db_insert = []
sat_orderline_list_for_db_insert = []
for orderline_key, orderline_item in orderline_dict.items() :
    hub_orderline_list_for_db_insert.append((orderline_item["hashkey"], orderline_item["product_hashkey"], orderline_item["order_hashkey"], today, "op_s_1"))
    sat_orderline_list_for_db_insert.append((orderline_item["hashkey"], today, "op_s_1",orderline_key,orderline_item["quantity"],orderline_item["priceeach"]))
cur.executemany(insert_stmnt_hub_orderlines, hub_orderline_list_for_db_insert)
cur.executemany(insert_stmnt_sat_orderlines, sat_orderline_list_for_db_insert)

conn.commit()
cur.close()
conn.close() 