package com.example.driverservice.config;

import com.example.driverservice.model.Driver;
import com.example.driverservice.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BirthdayGreeter {

    private final DriverRepository driverRepository;

    // congratulate every day on 00:00
    @Scheduled(cron = "0 0 0 * * *")
    public void greet() {
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        List<Driver> drivers = driverRepository.findByMatchMonthAndMatchDay(month, day);

        for (Driver driver : drivers) {
            System.out.println("Happy birthday to driver " + driver.getName());
        }
    }
}
