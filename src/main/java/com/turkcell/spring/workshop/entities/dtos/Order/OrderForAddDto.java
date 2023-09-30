package com.turkcell.spring.workshop.entities.dtos.Order;

import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForAddDto {

    private Date orderDate;
    private Date requiredDate;

    @Digits(integer = 1, fraction = 0, message = "Girilecek sayı tek basamak olmalıdır.")
    private short shipVia;

    private float freight;

    @NotBlank(message = "Ship Name girme zorunludur.")
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;
}
