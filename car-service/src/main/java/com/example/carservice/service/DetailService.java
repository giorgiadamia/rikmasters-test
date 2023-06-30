package com.example.carservice.service;

import com.example.carservice.model.Car;
import com.example.carservice.model.Detail;
import com.example.carservice.repository.CarRepository;
import com.example.carservice.repository.DetailRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;
    private final CarRepository carRepository;

    public void createDetail(Detail detail) {
        detailRepository.save(detail);
    }

    @Transactional
    public void setDetailOnCar(Long id, Long carId) {
        Detail detail = detailCheck(id);
        Optional<Car> carOptional = carRepository.findById(carId);

        if (carOptional.isEmpty()) {
            throw new EntityNotFoundException("There is no car with this id");
        }

        Car car = carOptional.get();

        List<Detail> details = car.getDetails();
        details.add(detail);
        car.setDetails(details);

        carRepository.save(car);
    }

    private Detail detailCheck(Long id) {
        Optional<Detail> detailOptional = detailRepository.findById(id);

        if (detailOptional.isEmpty()) {
            throw new EntityNotFoundException("There is no detail with this id");
        }

        return detailOptional.get();
    }
}
