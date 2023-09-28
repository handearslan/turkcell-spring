package com.turkcell.spring.workshop.business;

import com.turkcell.spring.workshop.entities.dtos.CategoryForListingDto;

import java.util.List;

public interface CategoryService {

    List<CategoryForListingDto> getAll();

}
