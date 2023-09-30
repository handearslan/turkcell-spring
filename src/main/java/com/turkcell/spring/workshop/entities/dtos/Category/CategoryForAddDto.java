package com.turkcell.spring.workshop.entities.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//hatalı ve istenmeyen durumları önlemek adına
//never trust user input
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForAddDto {

    @NotBlank(message = "Katagori adı girme zorunludur.")
    @Size(min=3)
    //@Pattern() //regex mesela şifre en az 1 harf bir sayı vs
    private String categoryName;

    @NotBlank(message = "description girmek zorunludur.")
    private String description;
}