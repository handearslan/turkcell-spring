package com.turkcell.spring.workshop.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class CategoryForListingDto {
    private int categoryId;
    private String categoryName;
}

    //http://localhost:8081/swagger-ui/index.html

