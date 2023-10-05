package com.turkcell.spring.workshop.entities.dtos.Product;

import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.Supplier;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {

    private int productID;

    @Size(min = 3, message = "Ürün adı 3 karakterden fazla olmalıdır. ")
    private String productName;

    @PositiveOrZero(message = "0 veya daha büyük olmalıdır.")
    @NotNull(message = "Boş bırakılmaz.")
    private int supplierID;

    @PositiveOrZero(message = "0 veya daha büyük olmalıdır.")
    @NotNull(message = "Boş bırakılmaz.")
    private int categoryId;

    @Min(value = 1, message = "En az 1 olmalıdır")
    private String quantityPerUnit;


    private float unitPrice;

    @PositiveOrZero(message = "Stoktaki ürün sayısı 0 veya daha büyük olmalıdır.")

    private short unitsInStock;
    private short unitsOnOrder;
    private String quantityUnit;
}


