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

data_source = "../data_processed/Daily_Values_Temperature_Measurements.tsv"
file_sql = "03-define_tables_DB_tempmeas.sql"

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

    try:
        cur.execute("DELETE FROM temperature_measurements;")
    except Exception as e:
        print("Error deleting temperature measurements:", e)

    list_to_insert = []
    counter = 0
    with open(data_source, 'r') as file:
        reader = csv.reader(file, delimiter='\t')
        # Skip the header
        next(reader)
        for line in reader:
            counter += 1
            station = line[0]
            day = line[1]
            temp = float(line[2])
            day_formatted = day[0:4] + "-" + day[4:6] + "-" + day[6:8]
            list_to_insert.append((counter,day_formatted, temp,station))
    
    list_for_db_insert = tuple(list_to_insert)
    print("- Temperature measurements element example: ", list_for_db_insert[0])

    # insert 1000 rows at a time
    for i in range(0, len(list_for_db_insert), 1000):
        try:
            cur.executemany("INSERT INTO temperature_measurements (measurement_id, date, temperature_average,station_id) VALUES (%s, %s, %s, %s);", list_for_db_insert[i:i+1000])
            print("Inserted ", i, " rows to the database.")
        except Exception as e:
            print("Error inserting temperature measurements:", e)


    print("Written ", counter, " rows to the database.")

    conn.commit()
    cur.close()
    conn.close()

    

if __name__ == "__main__":
    main()
