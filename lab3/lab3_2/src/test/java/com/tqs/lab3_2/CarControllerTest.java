package com.tqs.lab3_2;

import com.tqs.lab3_2.Controller.CarController;
import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Service.CarManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import net.minidev.json.JSONUtil;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_thenReturnCar() throws Exception{
        Car car1 = new Car("Mercedes-Benz","Classe A");

        when(service.save(Mockito.any())).thenReturn(car1);

        mvc.perform(
            post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car1)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Mercedes-Benz")))
            .andExpect(jsonPath("$.model", is("Classe A")));
        
        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJSONArray() throws Exception{
        Car car1 = new Car("Toyota","Yaris");
        Car car2 = new Car("Peugeot","308");
        Car car3 = new Car("Opel","Zafira");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car1.getModel())))
            .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
            .andExpect(jsonPath("$[1].model", is(car2.getModel())))
            .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
            .andExpect(jsonPath("$[2].model", is(car3.getModel())));

        verify(service,times(1)).getAllCars();
    }
}
