package com.turkcell.spring.workshop.entities.dtos.Order;

import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForAddDto {

    private LocalDate orderDate;
    private LocalDate requiredDate;
    private int employee_id;
    @Digits(integer = 1, fraction = 0, message = "Girilecek sayı tek basamak olmalıdır.")
    private short shipVia;

    private float freight;

    //@NotBlank(message = "Ship Name girme zorunludur.")
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;
}
