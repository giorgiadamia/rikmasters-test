package com.example.driverservice.controller;

import com.example.driverservice.model.Driver;
import com.example.driverservice.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public void createDriver(@RequestBody @Valid Driver driver) {
        driverService.createDriver(driver);
    }

    @GetMapping("/{id}")
    public Driver getDriver(@PathVariable Long id) {
        return driverService.getDriver(id);
    }

    @PutMapping("/{id}")
    public void updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        driverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}
