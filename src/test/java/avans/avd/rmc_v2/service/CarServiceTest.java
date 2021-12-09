package avans.avd.rmc_v2.service;


import avans.avd.rmc_v2.enums.CarType;
import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// test file is called a Unit
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarServiceTest {
    @Mock
    private CarRepository repository;

    @InjectMocks
    private CarService carService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // every test is called a spec
    @Test
    void testGetAllCars() {
        // Assign
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1L, "Honda", "H-RV", "EX-L", "465-HK-3", 5.7, 33450, 0, CarType.ICE, new User(), LocalDateTime.now()));
        cars.add(new Car(2L,"Volkswagen", "Polo", "1.6 TDI", "4-YY-685", 4.45, 25960, 0, CarType.ICE, new User(), LocalDateTime.now()));
        cars.add(new Car(3L, "Hyundai", "ix35", "FCEV", "739-PD-2", 0.59, 27050, 0, CarType.FCEV, new User(),  LocalDateTime.now()));

        // Act
        when(repository.findAll()).thenReturn(cars);
        List<Car> results = carService.getAllCars();

        // Assert
        assertEquals(cars.size(), results.size());
    }

    @Test
    void testCreateCar() {
        // Assign
        Car car = new Car(5L, "Honda", "H-RV", "EX-L", "465-HK-3", 5.7, 33450, 0, CarType.ICE, new User(), LocalDateTime.now());

        // Act
        when(repository.save(car)).thenReturn(car);
        Car result = carService.createCar(car);

        // Assert
        assertEquals(car.getBrand(), result.getBrand());
    }

    //todo: Make Update Test & Delete Test for Car
}
