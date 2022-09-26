package ru.gorodovikov.MeasurementsApp.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.gorodovikov.MeasurementsApp.models.Measurement;

import java.util.Random;

public class TestUtils {

    public static String randomStringGenerator(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Response sensorPostRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/sensors/registration")
                .then()
                .extract().response();
    }

    public static String sensorJson(String sensorName) {
        return "{" + "\"name\": \"" + sensorName + "\"}";
    }


    public static Response measurementPostRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/measurements/add")
                .then()
                .extract().response();
    }


    public static String measurementJson(Measurement measurement) {
        return "{" +
                "\"value\": \"" + measurement.getValue() + "\"," +
                "\"raining\": \"" + measurement.isRaining() + "\"," +
                "\"sensor\": {" +
                "\"name\": \"" + measurement.getSensor().getName() + "\"}" +
                "}";
    }

    public static String measurementJsonWithoutValue(Measurement measurement) {
        return "{" +
                "\"raining\": \"" + measurement.isRaining() + "\"," +
                "\"sensor\": {" +
                "\"name\": \"" + measurement.getSensor().getName() + "\"}" +
                "}";
    }

    public static String measurementJsonWithoutRainingCheck(Measurement measurement) {
        return "{" +
                "\"value\": \"" + measurement.getValue() + "\"," +
                "\"sensor\": {" +
                "\"name\": \"" + measurement.getSensor().getName() + "\"}" +
                "}";
    }

    public static String measurementJsonWithoutSensor(Measurement measurement) {
        return "{" +
                "\"value\": \"" + measurement.getValue() + "\"," +
                "\"raining\": \"" + measurement.isRaining() + "\"," +
                "}";
    }
}
