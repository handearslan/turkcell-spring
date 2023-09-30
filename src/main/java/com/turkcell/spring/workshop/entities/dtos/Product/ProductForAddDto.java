package com.turkcell.spring.workshop.entities.dtos.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForAddDto {
    private String productName;
    private String quantityPerUnit;
    private float unitPrice;

    @PositiveOrZero(message = "Stoktaki ürün sayısı 0 veya daha büyük olmalıdır.")
    private short unitsInStock;

    private short unitsOnOrder;
    private short reorderLevel;

    @NotBlank(message = "Boş bırakılmaz.")
    private String quantityUnit;
}

