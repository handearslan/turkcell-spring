package com.turkcell.spring.workshop.entities.dtos.Order;

import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForUpdateDto {

    @Positive(message = "{zeroOrThan}")
    private int orderId;
    private LocalDate orderDate;
    private LocalDate requiredDate;

    @Digits(integer = 1, fraction = 0, message = "{numberSingleDigit}")
    private short shipVia;

    private float freight;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;


}
