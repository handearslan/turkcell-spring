package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.CategoryService;
import com.turkcell.spring.workshop.core.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForUpdateDto;
import com.turkcell.spring.workshop.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private final MessageSource messageSource;



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
        category.setDescription(request.getDescription());

        Category category = Category.builder().build();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);*/
        Category categoryFromAutoMapping = modelMapper.map(request, Category.class);
        categoryRepository.save(categoryFromAutoMapping);
    }


    private void categoryWithSameNameShouldNotExist(String categoryName) {
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null)
            throw new BusinessException(
                    messageSource.getMessage("categoryAllReady", null
                            , LocaleContextHolder.getLocale()));

    }

    private void categoryNameCanNotBeEmpty(String categoryName) {
        Category categoryNameIsEmpty = categoryRepository.findByCategoryName(categoryName);
        if (categoryNameIsEmpty != null)
            throw new BusinessException
                    (messageSource.getMessage("categoryNotEmpty", null, LocaleContextHolder.getLocale()));    }

    @Override
    public void updateCategory(int categoryId, CategoryForUpdateDto category) {
        /*Category categoryToUpdate = returnCategoryByIdIfExists(categoryId);

        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryToUpdate.setDescription(category.getDescription());

        categoryRepository.save(categoryToUpdate);*/
        Category categoryFromAutoMapping = modelMapper.map(category, Category.class);
        categoryRepository.save(categoryFromAutoMapping);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        Category categoryToDelete = returnCategoryByIdIfExists(categoryId);

        categoryRepository.delete(categoryToDelete);
    }

    private Category returnCategoryByIdIfExists(int id){
        Category categoryToDelete = categoryRepository.findById(id).orElse(null);
        if(categoryToDelete==null)
            throw new BusinessException(
                    messageSource.getMessage("categoryDoesNotExistWithGivenId", new Object[] {id}, LocaleContextHolder.getLocale()));
        return categoryToDelete;
    }
}




