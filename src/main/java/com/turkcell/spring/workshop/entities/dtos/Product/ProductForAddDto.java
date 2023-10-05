package com.turkcell.spring.workshop.entities.dtos.Product;

import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.Supplier;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForAddDto {

    @Size(min = 3, message = "Ürün adı 3 karakterden fazla olmalıdır. ")
    private String productName;
    private String quantityPerUnit;

    private float unitPrice;

    @PositiveOrZero(message = "0 veya daha büyük olmalıdır.")
    @NotNull(message = "Boş bırakılmaz.")
    private int supplierID;

    @PositiveOrZero(message = "0 veya daha büyük olmalıdır.")
    @NotNull(message = "Boş bırakılmaz.")
    private int categoryId;

    @PositiveOrZero(message = "Stoktaki ürün sayısı 0 veya daha büyük olmalıdır.")
    private short unitsInStock;

    private short unitsOnOrder;
    private short reorderLevel;

    @NotBlank(message = "Boş bırakılmaz.")
    private String quantityUnit;
}


 /*Ürün ismi 3 haneden kısa olamaz.
        Supplier id ve category id boş geçilemez ve 0'dan küçük eşit olamaz
        Unit Price boş geçilemez ve 0'dan küçük olamaz
        Stock bilgisi 0dan küçük olamaz
        Birebir aynı isimde ikinci ürün eklenemez.*/
