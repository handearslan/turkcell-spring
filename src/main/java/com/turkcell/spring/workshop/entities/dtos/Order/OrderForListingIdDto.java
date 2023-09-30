package com.turkcell.spring.workshop.entities.dtos.Order;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForListingIdDto {
    private int orderId;
    private Date orderDate;
    private Date requiredDate;
    private short shipVia;
    private float freight;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipCountry;

}
