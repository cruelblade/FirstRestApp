package ru.gorodovikov.MeasurementsApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gorodovikov.MeasurementsApp.models.Measurement;
import ru.gorodovikov.MeasurementsApp.models.Sensor;
import ru.gorodovikov.MeasurementsApp.repositories.MeasurementsRepository;
import ru.gorodovikov.MeasurementsApp.util.SensorException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        log.info("Print all measurements");
        return measurementsRepository.findAll();
    }

    public Measurement findOne(int id) {
        log.info("Print measurement with id={}", id);
        return measurementsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        log.info("Added new measurement");
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        Optional<Sensor> sensor = sensorsService.findByName(measurement.getSensor().getName());

        if (sensor.isPresent()){
            measurement.setSensor(sensor.get());
        } else {
            throw new SensorException("Sensor does not found");
        }
    }
}
