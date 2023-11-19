package com.turkcell.spring.workshop.entities.dtos.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForListingIdDto {
    private int productID;
    private String productName;
    private String quantityPerUnit;
    private float unitPrice;
    private short unitsInStock;
    private short unitsOnOrder;
    private String quantityUnit;
    private short reorderLevel;

}
