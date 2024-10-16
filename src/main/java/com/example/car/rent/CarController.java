package com.example.car.rent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {
    private Map<String, Car> carDatabase = new HashMap<>();

    public CarController() {
        // Ajout de quelques voitures pour démonstration
        carDatabase.put("11AA22", new Car("11AA22", "Ferrari", 100));
        carDatabase.put("22BB33", new Car("22BB33", "Lamborghini", 200));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listOfCars() {
        return new ArrayList<>(carDatabase.values());
    }

    @GetMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        Car car = carDatabase.get(plateNumber);
        if (car == null) {
            throw new Exception("Car not found");
        }
        return car;
    }

    @PutMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody Dates dates) throws Exception {
        
        Car car = carDatabase.get(plateNumber);
        if (car == null) {
            throw new Exception("Car not found");
        }
        
        if (rent) {
            // Logique pour louer la voiture
            System.out.println("Car rented: " + plateNumber + " from " + dates.getBegin() + " to " + dates.getEnd());
        } else {
            // Logique pour récupérer la voiture
            System.out.println("Car returned: " + plateNumber);
        }
    }
}
