package com.turkcell.spring.workshop.entities.dtos.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForListingDto {
    private int categoryID;
    private String categoryName;
    private String description;
}

    //http://localhost:8081/swagger-ui/index.html

