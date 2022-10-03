package ru.gorodovikov.MeasurementsApp.util;

import ru.gorodovikov.MeasurementsApp.models.Measurement;
import ru.gorodovikov.MeasurementsApp.models.Sensor;

import java.util.Random;

public class TestValues {
    public static Random random = new Random();

    public final static String sensorValidName =
            "Test" + random.nextInt(10000);

    public final static String sensorDuplicatedName =
            "Test7695";

    public final static String sensorTooLongName =
            "TestBig" + random.nextInt(1000,9999);

    public final static String sensorTooShortName =
            TestUtils.randomStringGenerator(2);

    public final static Measurement validMeasurement =
            new Measurement((random.nextFloat(99)), random.nextBoolean(), new Sensor("Тайваньский сенсор"));

    public final static Measurement measurementWithTooHighValue =
            new Measurement(101, random.nextBoolean(), new Sensor("Тайваньский сенсор"));

    public final static Measurement measurementWithTooLowValue =
            new Measurement(-101, random.nextBoolean(), new Sensor("Тайваньский сенсор"));

    public final static Measurement measurementWithNotExistedSensor =
            new Measurement((random.nextFloat(99)), random.nextBoolean(), new Sensor("Териякский сенсор"));


}
