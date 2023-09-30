package com.turkcell.spring.workshop.entities.dtos.Order;

import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForUpdateDto {

    @Positive(message = " Gelecek değer pozitif olmak zorundadır")
    private int orderId;
    private Date orderDate;
    private Date requiredDate;

    @Digits(integer = 1, fraction = 0, message = "Girilecek sayı tek basamak olmalıdır.")
    private short shipVia;


    private float freight;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;

}
