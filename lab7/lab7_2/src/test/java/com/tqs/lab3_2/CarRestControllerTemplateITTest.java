package com.tqs.lab3_2;

import java.util.List;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Repository.CarRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
public class CarRestControllerTemplateITTest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRep;

    @AfterEach
    public void resetDb() {
        carRep.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car1, Car.class);

        List<Car> found = carRep.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Classe A");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        Car car2 = new Car("BMW","Serie 1");
        carRep.saveAndFlush(car1);
        carRep.saveAndFlush(car2);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET,null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Classe A","Serie 1");
    }
}
