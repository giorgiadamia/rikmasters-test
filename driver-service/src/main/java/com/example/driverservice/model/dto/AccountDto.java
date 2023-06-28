package com.example.driverservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @JsonProperty("red_dollar_balance")
    private Double redDollarBalance;

    @JsonProperty("green_dollar_balance")
    private Double greenDollarBalance;

    @JsonProperty("blue_dollar_balance")
    private Double blueDollarBalance;
}
