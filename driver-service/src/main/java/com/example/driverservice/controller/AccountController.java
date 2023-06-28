package com.example.driverservice.controller;

import com.example.driverservice.model.Account;
import com.example.driverservice.model.dto.AccountDto;
import com.example.driverservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/{id}")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Account showBalance(@PathVariable Long id) {
        return accountService.showBalance(id);
    }

    @PutMapping("/red/add")
    public void addRedDollar(@PathVariable Long id,
                             @RequestBody AccountDto accountDto) {
        accountService.addRedDollar(id, accountDto);
    }

    @PutMapping("/red/sub")
    public void subRedDollar(@PathVariable Long id,
                             @RequestBody AccountDto accountDto) {
        accountService.subRedDollar(id, accountDto);
    }

    @PutMapping("/green/add")
    public void addGreenDollar(@PathVariable Long id,
                               @RequestBody AccountDto accountDto) {
        accountService.addGreenDollar(id, accountDto);
    }

    @PutMapping("/green/sub")
    public void subGreenDollar(@PathVariable Long id,
                               @RequestBody AccountDto accountDto) {
        accountService.subGreenDollar(id, accountDto);
    }

    @PutMapping("/blue/add")
    public void addBlueDollar(@PathVariable Long id,
                              @RequestBody AccountDto accountDto) {
        accountService.addBlueDollar(id, accountDto);
    }

    @PutMapping("/blue/sub")
    public void subBlueDollar(@PathVariable Long id,
                              @RequestBody AccountDto accountDto) {
        accountService.subBlueDollar(id, accountDto);
    }
}
