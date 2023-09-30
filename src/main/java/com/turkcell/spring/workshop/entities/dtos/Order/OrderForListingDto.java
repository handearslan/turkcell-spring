package com.turkcell.spring.workshop.entities.dtos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForListingDto {
    private int orderId;
    private java.sql.Date orderDate;
    private java.sql.Date requiredDate;
    private short shipVia;
    private float freight;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;

}
