package com.turkcell.spring.workshop.entities.dtos.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForListingDto {

    private int productID;
    private String productName;
    private String quantityPerUnit;
    private float unitPrice;
    private short unitsInStock;
    private short unitsOnOrder;
    private int discontinued;
    private String quantityUnit;
}
