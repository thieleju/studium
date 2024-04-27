# download specific file
wget https://opendata.dwd.de/climate_environment/CDC/observations_germany/climate/daily/kl/historical/KL_Tageswerte_Beschreibung_Stationen.txt -O ../data_raw/KL_Tageswerte_Beschreibung_Stationen.txt

# Download all stations based on the pattern
wget -r -np -nd -A 'tageswerte_KL_000*_20231231_hist.zip' -P ../data_raw/ https://opendata.dwd.de/climate_environment/CDC/observations_germany/climate/daily/kl/historical/

