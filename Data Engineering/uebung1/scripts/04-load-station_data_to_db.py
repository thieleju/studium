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

data_source = "../data_processed/Daily_Values_Description_Stations.tsv"
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

    # Drop tables if they exist
    try:
        cur.execute("DROP TABLE IF EXISTS stations CASCADE;")
        cur.execute("DROP TABLE IF EXISTS federal_states CASCADE;")
        cur.execute("DROP TABLE IF EXISTS temperature_measurements CASCADE;")
        print("Tables dropped successfully")
    except Exception as e:
        print("Error dropping tables:", e)

    # Execute the SQL file
    try:
        with open(file_sql, 'r') as file:
            query = file.read()
            cur.execute(query)
        # Commit changes
        conn.commit()
        print("Tables created successfully from sql file")
    except Exception as e:
        print("Error executing the sql file:", e)

    state_set = set()
    station_dict = {}
    # read the data from the file
    with open(data_source, 'r') as file:
        reader = csv.reader(file, delimiter="\t")
        # skip the header
        next(reader)
        for line in reader:
            state = line[2].strip()
            state_set.add(state)
            station_dict[line[0]] = {
                "name": line[1].strip(),
                "state": state
            }
    
    state_list=[]
    state_dict = {}
    for i,val in enumerate(state_set):
        state_list.append((i+1, val))
        state_dict[val] = i+1

    state_list_for_db_insert = tuple(state_list)
    station_list = []

    for key in station_dict:
        current_station_name = (station_dict[key])["name"]
        current_station_state = (station_dict[key])["state"]
        current_station_state_id = state_dict[current_station_state]
        station_list.append((key, current_station_name, current_station_state_id))

    station_list_for_db_insert = tuple(station_list)

    print("- State list element example:", state_list_for_db_insert[0])
    print("- Station list element example:", station_list_for_db_insert[0])

    # Insert states
    try: 
        cur.executemany("INSERT INTO federal_states (federal_state_id, name) VALUES (%s, %s);", state_list_for_db_insert)
        print("States inserted successfully")
    except Exception as e:
        print("Error inserting states:", e)

    # Insert stations
    try:
        cur.executemany("INSERT INTO stations (station_id, name, federal_state_id) VALUES (%s, %s, %s);", station_list_for_db_insert)
        print("Stations data inserted successfully")
    except Exception as e:
        print("Error inserting stations:", e)

    conn.commit()
    cur.close()
    conn.close()


if __name__ == "__main__":
    main()
