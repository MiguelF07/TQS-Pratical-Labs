package com.tqs.lab3_2;

import java.util.List;
import java.util.Optional;

import com.tqs.lab3_2.Model.Car;
import com.tqs.lab3_2.Repository.CarRepository;
import com.tqs.lab3_2.Service.CarManagerService;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    
    @Mock(lenient = true)
    private CarRepository carRep;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Mercedes-Benz","Classe A");
        Car car2 = new Car("Peugeot","308");

        car1.setCarId(23L);

        List<Car> allCars = Arrays.asList(car1,car2);

        Mockito.when(carRep.findByMaker(car1.getMaker())).thenReturn(Optional.of(car1));
        Mockito.when(carRep.findByModel(car1.getModel())).thenReturn(Optional.of(car1));
        Mockito.when(carRep.findByMaker(car2.getMaker())).thenReturn(Optional.of(car2));
        Mockito.when(carRep.findByModel(car2.getModel())).thenReturn(Optional.of(car2));
        Mockito.when(carRep.findByMaker("invalid maker")).thenReturn(null);
        Mockito.when(carRep.findByModel("invalid model")).thenReturn(null);
        Mockito.when(carRep.findById(car1.getCarId())).thenReturn(Optional.of(car1));
        Mockito.when(carRep.findById(-23L)).thenReturn(Optional.empty());
        Mockito.when(carRep.findAll()).thenReturn(allCars);

    }

    @Test
    public void whenSearchValidModel_thenCarShouldBeFound() {        
        String model = "Classe A";
        Optional<Car> foundOp = carService.getCarByModel(model);
        Car found = null;

        if(foundOp.isPresent()) {
            found = foundOp.get();
        }

        assertThat(found.getModel()).isEqualTo(model);
    }

    @Test
    public void whenSearchValidMaker_thenCarShouldBeFound() {
        String maker = "Peugeot";
        Optional<Car> foundOp = carService.getCarByMaker(maker);
        Car found = null;

        if(foundOp.isPresent()) {
            found = foundOp.get();
        }

        assertThat(found.getMaker()).isEqualTo(maker);
    }

    @Test
    public void whenSearchInvalidModel_thenCarShouldNotBeFound() {
        String model = "invalid_model";
        Optional<Car> foundOp = carService.getCarByModel(model);
        Car found = null;

        if(foundOp.isPresent()) {
            found = foundOp.get();
        }

        assertThat(found).isNull();

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByModel(model);
    }

    @Test
    public void whenSearchInvalidMaker_thenCarShouldNotBeFound() {
        String maker = "invalid_maker";
        Optional<Car> foundOp = carService.getCarByMaker(maker);
        Car found = null;

        if(foundOp.isPresent()) {
            found = foundOp.get();
        }

        assertThat(found).isNull();

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByMaker(maker);
    }

    @Test
     void whenValidModel_thenCarShouldExist() {
        boolean doesCarExist = carService.exists_model("Classe A");
        assertThat(doesCarExist).isEqualTo(true);

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByModel("Classe A");
    }

    @Test
     void whenValidMaker_thenCarShouldExist() {
        boolean doesCarExist = carService.exists_maker("Mercedes-Benz");
        assertThat(doesCarExist).isEqualTo(true);

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByMaker("Mercedes-Benz");
    }

    @Test
     void whenNonExistingModel_thenCarShouldNotExist() {
        boolean doesCarExist = carService.exists_model("A3");
        assertThat(doesCarExist).isEqualTo(false);

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByModel("A3");
    }

    @Test
     void whenNonExistingMaker_thenCarShouldNotExist() {
        boolean doesCarExist = carService.exists_maker("Audi");
        assertThat(doesCarExist).isEqualTo(false);

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findByMaker("Audi");
    }

    @Test
     void whenValidId_thenCarShouldBeFound() {
        Optional<Car> carOp = carService.getCarDetails(23L);
        Car car = null;
        if(carOp.isPresent()) {
            car = carOp.get();
        }
        assertThat(car.getModel()).isEqualTo("Classe A");

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

    @Test
     void whenInvalidId_thenCarShouldNotBeFound() {
        //TODO:Implement
        Optional<Car> carOp = carService.getCarDetails(-45L);
        Car car = null;
        if(carOp.isPresent()) {
            car = carOp.get();
        }
        assertThat(car).isNull();
        Mockito.verify(carRep, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

    @Test
     void given2Cars_whengetAll_thenReturn3Records() {

        Car car1 = new Car("Mercedes-Benz","Classe A");
        Car car2 = new Car("Peugeot","308");

        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).hasSize(2).extracting(Car::getModel).contains(car1.getModel(),car2.getModel());

        Mockito.verify(carRep, VerificationModeFactory.times(1)).findAll();
    }
}
