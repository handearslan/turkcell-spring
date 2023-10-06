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

        /*Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());*/

        Category category = Category.builder().build();
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
        Category categoryToUpdate = returnCategoryByIdIfExists(categoryId);

        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryToUpdate.setDescription(category.getDescription());

        categoryRepository.save(categoryToUpdate);

    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        Category categoryToDelete = returnCategoryByIdIfExists(categoryId);

        categoryRepository.delete(categoryToDelete);
    }

    private Category returnCategoryByIdIfExists(int id) {
        Category categoryToDelete = categoryRepository.findById(id).orElse(null);
        if (categoryToDelete == null)
            throw new BusinessException("Böyle bir kategori bulunamadı.");
        return categoryToDelete;
    }
}







