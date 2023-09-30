package com.turkcell.spring.workshop.entities.dtos.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForListingIdDto {
    private int categoryId;
    private String categoryName;
    private String description;
}
