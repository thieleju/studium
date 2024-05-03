CREATE TABLE stations (
  station_id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  federal_state_id INT NOT NULL
);

CREATE TABLE federal_states (
  federal_state_id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE temperature_measurements (
  measurement_id INT PRIMARY KEY,
  date DATE NOT NULL,
  temperature_average DECIMAL(4,1) NOT NULL,
  station_id INT NOT NULL
);

ALTER TABLE stations ADD CONSTRAINT fk_stations_federal_states
  FOREIGN KEY (federal_state_id) REFERENCES federal_states(federal_state_id);

ALTER TABLE temperature_measurements ADD CONSTRAINT fk_temp_meas_stations
  FOREIGN KEY (station_id) REFERENCES stations(station_id);
