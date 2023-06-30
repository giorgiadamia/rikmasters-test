package com.example.carservice.controller;

import com.example.carservice.model.Detail;
import com.example.carservice.model.dto.ApiResponse;
import com.example.carservice.service.DetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/detail")
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    @PostMapping
    public ResponseEntity<ApiResponse> createDetail(@RequestBody @Valid Detail detail) {
        detailService.createDetail(detail);

        ApiResponse response = new ApiResponse();
        response.setMessage("Detail successfully created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/car/{carId}")
    public ResponseEntity<ApiResponse> setDetailOnCar(@PathVariable Long id,
                                                      @PathVariable Long carId) {
        detailService.setDetailOnCar(id, carId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Detail successfully set on car");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
