package com.turkcell.spring.workshop.entities.dtos.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryForUpdateDto {

    @NotNull
    @Min(1)
    private int categoryId;


    @NotBlank(message = "{categoryNameRequired}")
    @Size(min = 3)
    private String categoryName;


    @NotBlank(message = "{descriptionRequired}W")
    private String description;
}

