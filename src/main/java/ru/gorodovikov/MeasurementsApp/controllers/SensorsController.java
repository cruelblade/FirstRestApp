package ru.gorodovikov.MeasurementsApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gorodovikov.MeasurementsApp.dto.SensorDTO;
import ru.gorodovikov.MeasurementsApp.models.Sensor;
import ru.gorodovikov.MeasurementsApp.services.MeasurementsService;
import ru.gorodovikov.MeasurementsApp.services.SensorsService;
import ru.gorodovikov.MeasurementsApp.util.*;
import ru.gorodovikov.MeasurementsApp.util.ErrorsUtil;
import ru.gorodovikov.MeasurementsApp.util.SensorErrorResponse;
import ru.gorodovikov.MeasurementsApp.util.SensorException;
import ru.gorodovikov.MeasurementsApp.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    public SensorsController(SensorsService sensorsService, MeasurementsService measurementsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<SensorDTO> getSensors() {
        return sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@Valid @RequestBody SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        sensorsService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
