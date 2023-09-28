package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.CategoryService;
import com.turkcell.spring.workshop.entities.dtos.CategoryForListingDto;
import com.turkcell.spring.workshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryForListingDto> getAll() {
        return categoryRepository.getForListing();
    }
}
// DTO => Data Transfer Object




