package com.tqs.lab3_2;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Repository.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRep;

    @BeforeEach
    void cleanDatabase() {
        entityManager.clear();
    }

    @Test
    void whenFindCarByModel_thenReturnCar() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        entityManager.persistAndFlush(car1);

        Optional<Car> foundOp = carRep.findByModel(car1.getModel());
        Car found = null;
        if(foundOp.isPresent())
        {
            found=foundOp.get();
        }
        assertThat(found).isEqualTo(car1);
    }

    @Test
    void whenFindCarByMaker_thenReturnCar() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        entityManager.persistAndFlush(car1);

        Optional<Car> foundOp = carRep.findByMaker(car1.getMaker());
        Car found = null;
        if(foundOp.isPresent())
        {
            found=foundOp.get();
        }
        assertThat(found).isEqualTo(car1);
    }

    @Test
    void whenInvalidCarMaker_thenReturnNull() {
        Optional<Car> foundOp = carRep.findByMaker("Not existent");
        Car found = null;
        if(foundOp.isPresent())
        {
            found=foundOp.get();
        }
        assertThat(found).isNull();
    }

    @Test
    void whenInvalidCarModel_thenReturnNull() {
        Optional<Car> foundOp = carRep.findByModel("Not existent");
        Car found = null;
        if(foundOp.isPresent())
        {
            found=foundOp.get();
        }
        assertThat(found).isNull();
    }

    @Test
    void whenFindCarByExistingId_thenReturnCar() {
        Car car1 = new Car("Volkswagen","Golf");
        entityManager.persistAndFlush(car1);

        Car found = carRep.findById(car1.getCarId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getModel()).isEqualTo(car1.getModel());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car found = carRep.findById(-98L).orElse(null);
        assertThat(found).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("Volkswagen","Golf");
        Car car2 = new Car("Mercedes-Benz","Classe A");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.flush();

        List<Car> cars = carRep.findAll();

        assertThat(cars).hasSize(2).extracting(Car::getModel).containsOnly(car1.getModel(),car2.getModel());

    }

}
