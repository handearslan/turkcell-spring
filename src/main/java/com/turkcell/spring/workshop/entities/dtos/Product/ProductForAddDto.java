package com.turkcell.spring.workshop.entities.dtos.Product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForAddDto {

    @Size(min = 3, message = "Ürün adı 3 karakterden fazla olmalıdır. ")
    private String productName;

    @Min(1)
    @NotNull(message = "Boş bırakılmaz.")
    private short supplierID;

    @Min(1)
    @NotNull(message = "Boş bırakılmaz.")
    private int categoryId;

    @Min(1)
    @NotNull(message = "Boş bırakılmaz.")
    private float unitPrice;

    @Min(1)
    @NotNull(message = "Boş bırakılmaz.")
    private short unitsInStock;

}


 /*Ürün ismi 3 haneden kısa olamaz.
        Supplier id ve category id boş geçilemez ve 0'dan küçük eşit olamaz
        Unit Price boş geçilemez ve 0'dan küçük olamaz
        Stock bilgisi 0dan küçük olamaz
        Birebir aynı isimde ikinci ürün eklenemez.*/
