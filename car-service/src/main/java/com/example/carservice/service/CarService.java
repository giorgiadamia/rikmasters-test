package com.example.carservice.service;

import com.example.carservice.model.Car;
import com.example.carservice.model.dto.DriverResponse;
import com.example.carservice.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final WebClient webClient;

    @Transactional
    public void createCar(Car car) {
        if (car.getDriver() != null) {
            Long driverId = car.getDriver();

            webClient.get()
                    .uri("http://localhost:8080/api/v1/driver" + "/" + driverId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new EntityNotFoundException("Driver with this id doesnt exits")))
                    .bodyToMono(DriverResponse.class)
                    .block();
        }

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
            Long driverId = car.getDriver();

            webClient.get()
                    .uri("http://localhost:8080/api/v1/driver" + "/" + driverId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new EntityNotFoundException("Driver with this id doesnt exits")))
                    .bodyToMono(DriverResponse.class)
                    .block();
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