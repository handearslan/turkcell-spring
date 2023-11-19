package com.turkcell.spring.workshop.business.abstracts;

import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForUpdateDto;

import java.util.List;

public interface CategoryService {

    List<CategoryForListingDto> getAll();

    List<CategoryForListingIdDto> getById(int categoryId);

    void addCategory(CategoryForAddDto request);

    void updateCategory(int categoryId, CategoryForUpdateDto category);

    void deleteCategory(int categoryId);

}