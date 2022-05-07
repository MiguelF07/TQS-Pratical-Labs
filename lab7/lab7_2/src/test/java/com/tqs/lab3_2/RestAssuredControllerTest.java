package com.tqs.lab3_2;

import com.tqs.lab3_2.Controller.CarController;
import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Service.CarManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.common.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;


@WebMvcTest(CarController.class)
public class RestAssuredControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    private Car car1;
    private Car car2;
    private Car car3;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
        car1 = new Car("Volkswagen","Golf");
        car2 = new Car("Toyota","Yaris");
        car3 = new Car("Peugeot","308");
    }

    @Test
    public void whenPostCar_thenReturnCar() throws Exception{
        when(service.save(car1)).thenReturn(car1);

        RestAssuredMockMvc.given().header("Content-type", "application/json").body(car1).when().post("/api/cars").then().assertThat().statusCode(201);
    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJSONArray() throws Exception{
        List<Car> allCars = Arrays.asList(car1, car2, car3);
        when( service.getAllCars()).thenReturn(allCars);

        List<Map<String, Object>> cars = RestAssuredMockMvc.given().when().get("/api/cars").as(new TypeRef<List<Map<String, Object>>>() {});
        assertThat(cars,hasSize(3));
        assertThat(cars.get(0).get("maker"),equalTo("Volkswagen"));
        assertThat(cars.get(0).get("model"),equalTo("Golf"));
        assertThat(cars.get(1).get("maker"),equalTo("Toyota"));
        assertThat(cars.get(1).get("model"),equalTo("Yaris"));
        assertThat(cars.get(2).get("maker"),equalTo("Peugeot"));
        assertThat(cars.get(2).get("model"),equalTo("308"));
    }
}
