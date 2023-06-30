package com.example.carservice.service;

import com.example.carservice.model.Car;
import com.example.carservice.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public void createCar(Car car) {
        // webflux to driverservice to check driver id
        carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carCheck(id);
    }

    @Transactional
    public void updateCar(Long id, Car car) {
        Car carFromMemory = carCheck(id);

        if (car.getVin() != null) {
            carFromMemory.setVin(car.getVin());
        }

        if (car.getStateNumber() != null) {
            carFromMemory.setStateNumber(car.getStateNumber());
        }

        if (car.getDetails() != null) {
            carFromMemory.setDetails(car.getDetails());
        }

        if (car.getManufacturer() != null) {
            carFromMemory.setManufacturer(car.getManufacturer());
        }

        if (car.getBrand() != null) {
            carFromMemory.setBrand(car.getBrand());
        }

        if (car.getYearOfIssue() != null) {
            carFromMemory.setYearOfIssue(car.getYearOfIssue());
        }

        if (car.getDriver() != null) {
            // webflux
            carFromMemory.setDriver(car.getDriver());
        }

        carRepository.save(carFromMemory);
    }

    public void deleteCar(Long id) {
        carRepository.delete(carCheck(id));
    }

    private Car carCheck(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isEmpty()) {
            throw new EntityNotFoundException("There is no car with this id");
        }
        return carOptional.get();
    }
}