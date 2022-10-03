package ru.gorodovikov.MeasurementsApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Value should not be empty")
    @Size(min = 3, max = 10, message = "Value should be from 3 to 10 symbols")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
