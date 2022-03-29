package com.tqs.lab3_2.Service;

import java.util.List;
import java.util.Optional;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car c) {
        return carRepository.save(c);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarDetails(Long det) {
        return carRepository.findById(det).orElse(null);
    }

    public Car getCarByModel(String model) {
        return carRepository.findByModel(model);
    }

    public Car getCarByMaker(String maker) {
        return carRepository.findByMaker(maker);
    }

    public boolean exists_model(String model) {
        if (carRepository.findByModel(model) != null) {
            return true;
        }
        return false;
    }

    public boolean exists_maker(String maker) {
        if (carRepository.findByMaker(maker) != null) {
            return true;
        }
        return false;
    }






    
}
