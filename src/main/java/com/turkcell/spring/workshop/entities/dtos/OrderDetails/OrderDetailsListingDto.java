package com.turkcell.spring.workshop.entities.dtos.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsListingDto {

    private int orderId;
    private String productName;
}