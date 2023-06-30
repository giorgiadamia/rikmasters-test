package com.example.driverservice.service;

import com.example.driverservice.exception.BadArgumentException;
import com.example.driverservice.exception.ResourceNotFoundException;
import com.example.driverservice.model.Account;
import com.example.driverservice.model.Driver;
import com.example.driverservice.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public void createDriver(Driver driver) {
        Driver driverFromMemory = driverRepository.getDriverByPassport(driver.getPassport());

        if (driverFromMemory != null) {
            throw new BadArgumentException("Driver with this passport already exist");
        }

        Account account = new Account();
        account.setBlueDollarBalance(BigDecimal.ZERO);
        account.setGreenDollarBalance(BigDecimal.ZERO);
        account.setRedDollarBalance(BigDecimal.ZERO);
        driver.setAccount(account);

        driverRepository.save(driver);
    }

    public Driver getDriver(Long id) {
        return driverCheck(id);
    }

    @Transactional
    public void updateDriver(Long id, Driver driver) {
        Driver driverFromMemory = driverCheck(id);

        if (driverFromMemory.getPassport().equals(driver.getPassport())) {
            throw new BadArgumentException("Driver with this passport already exist");
        }

        if (driver.getName() != null) {
            driverFromMemory.setName(driver.getName());
        }

        if (driver.getPassport() != null) {
            driverFromMemory.setPassport(driver.getPassport());
        }

        if (driver.getCategory() != null) {
            driverFromMemory.setCategory(driver.getCategory());
        }

        if (driver.getExperience() != null) {
            driverFromMemory.setExperience(driver.getExperience());
        }

        if (driver.getBirthday() != null) {
            driverFromMemory.setBirthday(driver.getBirthday());
        }

        if (driver.getAccount() != null) {
            driverFromMemory.setAccount(driver.getAccount());
        }

        driverRepository.save(driverFromMemory);
    }

    public void deleteDriver(Long id) {
        driverRepository.delete(driverCheck(id));
    }

    private Driver driverCheck(Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);

        if (driverOptional.isEmpty()) {
            throw new ResourceNotFoundException("There is no driver with this id");
        }

        return driverOptional.get();
    }
}
