package com.example.carservice.controller;

import com.example.carservice.model.Car;
import com.example.carservice.model.dto.ApiResponse;
import com.example.carservice.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/car")
@RequiredArgsConstructor
@Tag(name = "Car Controller", description = "Car API")
public class CarController {

    private final CarService carService;

    @PostMapping
    @Operation(summary = "Create car")
    public ResponseEntity<ApiResponse> createCar(@RequestBody @Valid Car car) {
        carService.createCar(car);

        ApiResponse response = new ApiResponse();
        response.setMessage("Car successfully created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by id")
    public ResponseEntity<ApiResponse> getCar(@PathVariable Long id) {
        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully get car by id");

        response.setObject(carService.getCar(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update car")
    public ResponseEntity<ApiResponse> updateCar(@PathVariable Long id,
                                                 @RequestBody Car car) {
        carService.updateCar(id, car);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully update car");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete car")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully delete car");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/driver/{driverId}")
    @Operation(summary = "Driver selection for the car")
    public ResponseEntity<ApiResponse> setDriver(@PathVariable Long id,
                                                 @PathVariable Long driverId) {
        carService.setDriver(id, driverId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Driver successfully set to car");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCars(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);

        ApiResponse response = new ApiResponse();

        response.setObject(carService.getAllCars(pageable));
        response.setMessage("Cars successfully extracted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
