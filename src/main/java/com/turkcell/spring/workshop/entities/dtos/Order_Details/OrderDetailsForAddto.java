package com.turkcell.spring.workshop.entities.dtos.Order_Details;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsForAddto {

    private int productId;
    private short quantity;

}
