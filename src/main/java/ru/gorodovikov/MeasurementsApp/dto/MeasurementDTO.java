package ru.gorodovikov.MeasurementsApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @Min(value = -100, message = "Input value > -101")
    @Max(value = 100, message = "Input value < 101")
    @NotNull(message = "Value should not be empty")
    private Float value;

    @NotNull(message = "Value should not be empty")
    private Boolean raining;

    private SensorDTO sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
