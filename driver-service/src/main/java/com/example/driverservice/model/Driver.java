package com.example.driverservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name cant be empty")
    private String name;

    @Column(name = "passport")
    @NotBlank(message = "Passport cant be empty")
    private String passport;

    @Column(name = "category")
    @NotBlank(message = "Category cant be empty")
    private String category;

    @Column(name = "birthday", columnDefinition = "DATE")
    @NotNull(message = "Birthday cant be null")
    private LocalDate birthday;

    @Column(name = "experience")
    @NotNull(message = "Experience cant be null")
    private Integer experience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
