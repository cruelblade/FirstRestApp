package ru.gorodovikov.MeasurementsApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gorodovikov.MeasurementsApp.models.Sensor;
import ru.gorodovikov.MeasurementsApp.repositories.SensorsRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class SensorsService {
    SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        log.info("Print all sensors");
        return sensorsRepository.findAll();
    }

    public Sensor findOne(int id) {
        log.info("Print sensor={}", id);
        return sensorsRepository.findById(id).orElse(null);
    }

    public Optional<Sensor> findByName(String name) {
        log.info("Found sensor '{}'", name);
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        log.info("Added new sensor '{}'", sensor.getName());
        sensorsRepository.save(sensor);
    }

}
