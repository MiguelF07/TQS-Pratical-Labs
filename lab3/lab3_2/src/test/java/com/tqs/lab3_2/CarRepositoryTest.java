package com.tqs.lab3_2;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Repository.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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

        Car found = carRep.findByModel(car1.getModel());
        assertThat(found).isEqualTo(car1);
    }

    @Test
    void whenFindCarByMaker_thenReturnCar() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        entityManager.persistAndFlush(car1);

        Car found = carRep.findByMaker(car1.getMaker());
        assertThat(found).isEqualTo(car1);
    }

    @Test
    void whenInvalidCarMaker_thenReturnNull() {
        Car found = carRep.findByMaker("Not existent");
        assertThat(found).isNull();
    }

    @Test
    void whenInvalidCarModel_thenReturnNull() {
        Car found = carRep.findByModel("Not existent");
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

}
