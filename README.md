# MeasurementsApp
This project with rest architecture, which takes data from weather sensors.
It cat take requests:

create new sensor
POST http://localhost:8080/sensors/registration

get all sensors
GET http://localhost:8080/sensors/

create new measurement
POST http://localhost:8080/measurements/add

get all measurements
GET http://localhost:8080/measurements/

get rainy days count from all measurements
GET http://localhost:8080/rainy_days_count/
