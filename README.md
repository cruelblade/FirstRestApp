# MeasurementsApp
This project with rest architecture, which takes data from weather sensors.
It can take requests:

create new sensor
--
POST ../sensors/registration

get all sensors
--
GET ../sensors/

create new measurement
--
POST ../measurements/add

get all measurements
--
GET ../measurements/

get rainy days count from all measurements
--
GET ../rainy_days_count/
