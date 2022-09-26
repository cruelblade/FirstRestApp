package ru.gorodovikov.MeasurementsApp;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.*;
import ru.gorodovikov.MeasurementsApp.models.Sensor;
import ru.gorodovikov.MeasurementsApp.services.MeasurementsService;
import ru.gorodovikov.MeasurementsApp.services.SensorsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.gorodovikov.MeasurementsApp.util.TestUtils.*;
import static ru.gorodovikov.MeasurementsApp.util.TestValues.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeasurementsAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
class MeasurementsAppApplicationTests {

	@Spy
	SensorsService sensorsService;

	@Spy
	MeasurementsService measurementsService;

	@Autowired
	public MeasurementsAppApplicationTests(SensorsService sensorsService, MeasurementsService measurementsService) {
		this.sensorsService = sensorsService;
		this.measurementsService = measurementsService;
	}

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		RestAssured.port = port;
	}

	@Test
	void sensorShouldBeCreated() {
		Response response = sensorPostRequest(sensorJson(sensorValidName));

		assertEquals(HttpStatus.OK.value(), response.statusCode());
		Assertions.assertNotNull(sensorsService.findByName(sensorValidName));
	}

	@Test
	void sensorShouldNotBeDuplicated() {
		Response response = sensorPostRequest(sensorJson(sensorDuplicatedName));

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void sensorShouldHaveValidLengthOfValues() {
		Response response = sensorPostRequest(sensorJson(sensorTooLongName));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());

		response = sensorPostRequest(sensorJson(sensorTooShortName));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}
	
	@Test
	void measurementsShouldBePosted() {
		Response response = RestAssured.when().get("/measurements");
		assertEquals(HttpStatus.OK.value(), response.statusCode());
	}

	@Test
	void measurementsShouldBeCreated() {
		Response response = measurementPostRequest(measurementJson(validMeasurement));
		assertEquals(HttpStatus.OK.value(), response.statusCode());

		// Для ассерта необходимо получить id measurements при пост запросе
		// Assertions.assertNotNull(measurementsService.findOne());
	}

	@Test
	void measurementsShouldHaveValue() {
		Response response = measurementPostRequest(measurementJsonWithoutValue(validMeasurement));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void measurementsShouldHaveValidLengthOfValues() {
		Response response = measurementPostRequest(measurementJson(measurementWithTooLowValue));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());

		response = measurementPostRequest(measurementJson(measurementWithTooHighValue));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void measurementsShouldHaveRainingCheck() {
		Response response = measurementPostRequest(measurementJsonWithoutRainingCheck(validMeasurement));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void measurementsShouldHaveSensor() {
		Response response = measurementPostRequest(measurementJsonWithoutSensor(validMeasurement));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void measurementsShouldHaveOnlyExistedSensor() {
		Response response = measurementPostRequest(measurementJson(measurementWithNotExistedSensor));
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
	}

	@Test
	void mockTest() {
		Sensor newSensor = Mockito.mock(Sensor.class);
//		newSensor.setName("Сенсор");
		when(newSensor.getName()).thenReturn("Сенсор");
		assertEquals("Сенсор", newSensor.getName());
	}


}
