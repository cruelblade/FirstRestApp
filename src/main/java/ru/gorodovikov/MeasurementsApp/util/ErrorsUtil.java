package ru.gorodovikov.MeasurementsApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append("Field \'")
                    .append(error.getField())
                    .append("\' - ").append(error.getDefaultMessage())
                    .append(";");
        }
        throw new SensorException(errorMsg.toString());
    }
}
