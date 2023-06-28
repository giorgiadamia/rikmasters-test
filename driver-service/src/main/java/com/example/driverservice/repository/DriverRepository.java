package com.example.driverservice.repository;

import com.example.driverservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver getDriverByPassport(String passport);

    @Query(value = "select * from drivers " +
            "where birthday is not null " +
            "AND extract(MONTH from birthday) = :m " +
            "AND extract(DAY from birthday) = :d",
            nativeQuery = true)
    List<Driver> findByMatchMonthAndMatchDay(@Param("m") int month, @Param("d") int day);
}
