package com.turkcell.spring.workshop.entities.dtos.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {

    private int productID;

    @Size(min = 1, max = 20, message = "Ürün adı 1 ile 5 karakter arasında olmalıdır ")
    private String productName;

    @Min(value = 1, message = "En az 1 olmalıdır")
    private String quantityPerUnit;

    private float unitPrice;
    private short unitsInStock;
    private short unitsOnOrder;
    private String quantityUnit;
}


