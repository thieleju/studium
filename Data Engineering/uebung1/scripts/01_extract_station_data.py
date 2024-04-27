import os
import re 

# Encoding is cp1252
file_stations = "../data_raw/KL_Tageswerte_Beschreibung_Stationen.txt" 

# Encoding is UTF-8 (Tab-separated values)
file_stations_out = "../data_processed/Daily_Values_Description_Stations.tsv"

headers_out = ["STATIONS_ID", "NAME", "FEDERAL_STATE"]

def main():
    # Check if file exists
    if not os.path.exists(file_stations):
        print(f"File {file_stations} not found.")
        return

    print("Extracting station data...")

    # if file exists, delete it
    if os.path.exists(file_stations_out):
        os.remove(file_stations_out)

    # Open file and read line by line
    with open(file_stations, "r", encoding="cp1252") as f:
        write_line_to_file(file_stations_out, "\t".join(headers_out))

        stations_data = []

        for i, line in enumerate(f):
            if i % 100 == 0:
                print(f"Processing lines {i} - {i+100}...")

            # Skip first two lines
            if i < 2:
                continue

            # Split line by spaces and remove empty elements (bit hacky, but works)
            parts = " ".join(line.split()).split(" ")

            # Extract data based on expected positions in the split parts
            station_id = parts[0]
            # Combine remaining parts (station name and state)
            station_name_state = " ".join(parts[6:])
            # Split station name and state based on the last space
            station_name, state = station_name_state.rsplit(" ", 1)

            # Append extracted data as a dictionary to the list
            stations_data.append({
                "STATIONS_ID": station_id,
                "NAME": station_name,
                "FEDERAL_STATE": state,
            })

        # Write extracted data to the output file
        for station in stations_data:
            line = "\t".join([station[header] for header in headers_out])
            write_line_to_file(file_stations_out, line)


def write_line_to_file(file, line):
    with open(file, "a", encoding="utf-8") as f_out:
        f_out.write(line + "\n")

if __name__ == "__main__":
    main()
