package com.example.carservice.controller;

import com.example.carservice.model.dto.ApiResponse;
import com.example.carservice.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/money/{driverId}")
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;

    @PutMapping("/red-withdraw/{amount}")
    public ResponseEntity<ApiResponse> redWithdraw(@PathVariable Long driverId,
                                                   @PathVariable Double amount) {
        moneyService.redWithdraw(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully withdrew red dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/red-deposit/{amount}")
    public ResponseEntity<ApiResponse> redDeposit(@PathVariable Long driverId,
                                                   @PathVariable Double amount) {
        moneyService.redDeposit(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully deposited red dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/blue-withdraw/{amount}")
    public ResponseEntity<ApiResponse> blueWithdraw(@PathVariable Long driverId,
                                                  @PathVariable Double amount) {
        moneyService.blueWithdraw(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully withdrew blue dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/blue-deposit/{amount}")
    public ResponseEntity<ApiResponse> blueDeposit(@PathVariable Long driverId,
                                                  @PathVariable Double amount) {
        moneyService.blueDeposit(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully deposited blue dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/green-withdraw/{amount}")
    public ResponseEntity<ApiResponse> greenWithdraw(@PathVariable Long driverId,
                                                    @PathVariable Double amount) {
        moneyService.greenWithdraw(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully withdrew green dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/green-deposit/{amount}")
    public ResponseEntity<ApiResponse> greenDeposit(@PathVariable Long driverId,
                                                   @PathVariable Double amount) {
        moneyService.greenDeposit(driverId, amount);

        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully deposited green dollar");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
