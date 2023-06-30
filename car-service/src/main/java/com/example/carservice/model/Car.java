package com.example.carservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "vin")
    private String vin;

    @NotBlank
    @Column(name = "state_number")
    private String stateNumber;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    List<Detail> details;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year_of_issue")
    private Integer yearOfIssue;

    @Column(name = "driver")
    private Long driver;
}
