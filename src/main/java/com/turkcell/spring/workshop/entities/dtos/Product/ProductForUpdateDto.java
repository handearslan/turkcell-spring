package com.turkcell.spring.workshop.entities.dtos.Product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {

    private int productID;

    @Size(min = 3, message = "{productNameShouldBeMinimum}")
    private String productName;

    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private int supplierID;

    @PositiveOrZero(message = "{zeroOrThan}")
    @NotNull(message = "{notNull}")
    private int categoryId;

    @Min(value = 1, message = "{minOne}")
    private String quantityPerUnit;


    private float unitPrice;

    @PositiveOrZero(message = "{zeroOrThan}")

    private short unitsInStock;
    private short unitsOnOrder;
    private String quantityUnit;
}