package com.turkcell.spring.workshop.entities.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryForUpdateDto {

    @NotBlank(message = "Kategori adı girmek zorunludur.")
    @Size(max = 20, min = 3)
    private String categoryName;


    @NotBlank(message = "Açıklama alanı zorunludur.")
    @Size(max = 500)
    private String description;
}

