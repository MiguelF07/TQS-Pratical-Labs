package com.tqs.lab3_2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tqs.lab3_2.Model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByModel(String model);
    public Car findByMaker(String maker);
    public List<Car> findAll();
}
