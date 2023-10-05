package com.turkcell.spring.workshop.entities.dtos.Order;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForListingIdDto {
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
