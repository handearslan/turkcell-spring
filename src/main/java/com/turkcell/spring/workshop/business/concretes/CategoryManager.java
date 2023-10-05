package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.CategoryService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForUpdateDto;
import com.turkcell.spring.workshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<CategoryForListingIdDto> getById(int categoryId) {

        return categoryRepository.getForListingId(categoryId);
    }

    @Override
    public void addCategory(CategoryForAddDto request) {
        categoryWithSameNameShouldNotExist(request.getCategoryName());
        categoryNameCanNotBeEmpty(request.getCategoryName());

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);
    }

    private void categoryWithSameNameShouldNotExist(String categoryName) {
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null)
            throw new BusinessException("Aynı kategori mevcut.Başka kategori ismi giriniz.");
    }

    private void categoryNameCanNotBeEmpty(String categoryName) {
        Category categoryNameIsEmpty = categoryRepository.findByCategoryName(categoryName);
        if (categoryNameIsEmpty != null)
            throw new BusinessException("Kategori adı boş olamaz.");
    }

    @Override
    public void updateCategory(int categoryId, CategoryForUpdateDto category) {
        categoryIdUpdateCouldNotBeFound(categoryId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();

            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setDescription(category.getDescription());

            categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    private void categoryIdUpdateCouldNotBeFound(int CategoryId) {
        Category categoryWithSameId = categoryRepository.findByCategoryId(CategoryId);
        if (categoryWithSameId == null) {
            throw new BusinessException("Kategori bulunamadı");
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            categoryRepository.delete(existingCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}







