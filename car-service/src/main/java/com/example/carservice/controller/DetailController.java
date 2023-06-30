package com.example.carservice.controller;

import com.example.carservice.model.Detail;
import com.example.carservice.model.dto.ApiResponse;
import com.example.carservice.service.DetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/detail")
@RequiredArgsConstructor
@Tag(name = "Detail Controller", description = "Detail API")
public class DetailController {

    private final DetailService detailService;

    @PostMapping
    @Operation(summary = "Create detail")
    public ResponseEntity<ApiResponse> createDetail(@RequestBody @Valid Detail detail) {
        detailService.createDetail(detail);

        ApiResponse response = new ApiResponse();
        response.setMessage("Detail successfully created");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/car/{carId}")
    @Operation(summary = "Set detail on car")
    public ResponseEntity<ApiResponse> setDetailOnCar(@PathVariable Long id,
                                                      @PathVariable Long carId) {
        detailService.setDetailOnCar(id, carId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Detail successfully set on car");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
