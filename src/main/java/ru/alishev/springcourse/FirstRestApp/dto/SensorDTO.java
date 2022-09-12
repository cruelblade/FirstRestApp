package ru.alishev.springcourse.FirstRestApp.dto;

import ru.alishev.springcourse.FirstRestApp.models.Measurement;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SensorDTO {

    @NotEmpty(message = "Эй, пустое не вводи, падла (DTO)")
    @Size(min = 3, max = 10, message = "Слышь вводи не меньше 3 и не больше 10, а то уебу (DTO)")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
