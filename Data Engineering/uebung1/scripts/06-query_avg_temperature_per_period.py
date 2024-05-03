import os
from dotenv import load_dotenv
import psycopg2 as db
import csv

load_dotenv()

DB_HOST="10.1.1.1"
DB_PORT=5432
DB_NAME="data_engineering"
DB_USER="postgres"
DB_PASSWORD = os.environ.get('POSTGRES_PASSWORD')

destination_file = "../data_processed/Average_daily_temperatures_per_period_for_bavaria.tsv"
header_destination = ["TIME_PERIOD", "AVG_TEMPERATURE","COUNT_DATAPOINTS"]
time_period1 = ["1960-01-01", "1980-12-31"]
time_period2 = ["2000-01-01", "2020-12-31"]
state = "Bayern"

def main():
    conn = None
    if not DB_PASSWORD:
        raise Exception("Please set the POSTGRES_PASSWORD environment variable")

    # Connect to the database
    try:
        conn = db.connect(
            host=DB_HOST,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD,
            port=DB_PORT
        )
    except Exception as e:
        print("Error connecting to the database:", e)
        exit(1)

    # Create cursor to execute queries
    cur = conn.cursor()
    query1 = f"select avg(temperature_average), count(temperature_average)  from temperature_measurements where date between '{time_period1[0]}' and '{time_period1[1]}' and station_id in (select station_id from stations where federal_state_id in (select federal_state_id from federal_states where name = '{state}'));"

    query2 = f"select avg(temperature_average), count(temperature_average)  from temperature_measurements where date between '{time_period2[0]}' and '{time_period2[1]}' and station_id in (select station_id from stations where federal_state_id in (select federal_state_id from federal_states where name = '{state}'));"

    print("Query1: ",query1)
    cur.execute(query1)
    result1 = cur.fetchone()
    print("- Result1: ",result1)
    if result1 == None:
        result1 = (0,0)
        print("ERROR: Result1 is None")

    print("Query2: ",query2)
    cur.execute(query2)
    result2 = cur.fetchone()
    print("- Result2: ",result2)
    if result2 == None:
        result2 = (0,0)
        print("ERROR: Result2 is None")

    # Write the results to the output file
    df = open(destination_file, "w",encoding="utf-8")
    df.write("\t".join(header_destination) + "\n")

    label1 = time_period1[0] + "/" + time_period1[1]
    label2 = time_period2[0] + "/" + time_period2[1]

    df.write("\t".join([label1, str(round(result1[0],2)), str(round(result1[1],2))]) + "\n")
    df.write("\t".join([label2, str(round(result2[0],2)), str(round(result2[1],2))]) + "\n")

    print("\n=> Results written to", destination_file)

    # print content of destination file
    df = open(destination_file, "r",encoding="utf-8")
    print(df.read())

    df.close()
    cur.close()
    conn.close()

if __name__ == "__main__":
    main()
