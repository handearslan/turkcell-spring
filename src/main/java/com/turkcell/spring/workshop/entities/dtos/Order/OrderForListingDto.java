package com.turkcell.spring.workshop.entities.dtos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForListingDto {
    private int orderId;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private short shipVia;
    private float freight;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;
}