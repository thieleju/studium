import os
import re
import zipfile

directory_measurements = "../data_raw/"
file_measurements_out = "../data_processed/Daily_Values_Temperature_Measurements.tsv"

headers_out = ["STATIONS_ID", "DAY", "TEMPERATURE_AVERAGE"]

def main():
    print("Extracting temperature data...")

    # if file exists, delete it
    if os.path.exists(file_measurements_out):
        os.remove(file_measurements_out)

    # write headers to file
    write_line_to_file(file_measurements_out, "\t".join(headers_out))
    
    # extract all zip files in the directory
    for file in os.listdir(directory_measurements):
        if file.endswith(".zip"):
            with zipfile.ZipFile(directory_measurements + file, "r") as zip_ref:
                zip_ref.extractall(directory_measurements)

    # get all measurement files
    measurement_files = [directory_measurements + file for file in os.listdir(directory_measurements) if file.startswith("produkt")]
    print(measurement_files)

    # iterate over all measurement files
    for file in measurement_files:
        process_temperature_file(file)

    # cleanup extracted files
    print("Cleaning up extracted files...")
    matching_files = r"(produkt.+|Metadaten.+)"
    for file in os.listdir(directory_measurements):
        if re.match(matching_files, file):
            os.remove(directory_measurements + file)


def process_temperature_file(file):
    with open(file, "r", encoding="utf-8") as f:
        for i, line in enumerate(f):
            if i % 500 == 0:
                print(f"Processing file {file} lines {i} - {i+500}...")

            # Skip first two lines
            if i < 2:
                continue

            parts = [part.strip() for part in line.split(";")]

            # Extract data based on expected positions in the split parts
            station_id = parts[0]
            day = parts[1]
            temp_avg = parts[13]

            # Skip lines if temp value is -999
            if temp_avg == "-999":
                continue

            # write line to file
            line = "\t".join([station_id, day, temp_avg])
            write_line_to_file(file_measurements_out, line)

def write_line_to_file(file, line):
    with open(file, "a", encoding="utf-8") as f_out:
        f_out.write(line + "\n")


if __name__ == "__main__":
    main()
