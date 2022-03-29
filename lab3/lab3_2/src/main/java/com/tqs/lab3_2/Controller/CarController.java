package com.tqs.lab3_2.Controller;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Service.CarManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    public ResponseEntity<Car> createCar(Car c)
    {
        //TODO:Implement
        return null;
    }

    public List<Car> getAllCars() {
        //TODO:Implement
        return null;

    }

    public ResponseEntity<Car> getCarById(Long id) {
        //TODO:Implement
        return null;
    }
    
}
