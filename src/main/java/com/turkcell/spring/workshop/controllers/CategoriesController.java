package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.abstracts.CategoryService;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Category.CategoryForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//http://localhost:8081/categories
@RestController
@RequestMapping("categories")
public class CategoriesController {

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryForListingDto> getCategories() {
        List<CategoryForListingDto> categoriesInDb = categoryService.getAll();
        return categoriesInDb;
    }

    @GetMapping("getById/{categoryId}")
    public List<CategoryForListingIdDto> getById(@PathVariable("categoryId") int categoryId) {
        return categoryService.getById(categoryId);
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid CategoryForAddDto category) {
        categoryService.addCategory(category);
        return new ResponseEntity(category.getCategoryName() + " adlı kategori eklendi.", HttpStatus.CREATED);
    }

    @PutMapping("updateCategory/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody @Valid CategoryForUpdateDto category) {
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity(category.getCategoryName() + "adlı kategori güncellendi", HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity("Kategori silindi", HttpStatus.OK);
    }

    @GetMapping("getByName")
    public List<Category> getCategoriesByName(@RequestParam("name") String name) {
        // List<Category> categories = categoryRepository.findByCategoryNameContaining(name);
        return null;
    }

  /*  @PostMapping()
    public ResponseEntity add(@RequestBody @Valid CategoryForAddDto request){
        // Manual Mapleme
        // Auto Mapper => ModelMapper
        //Category category = new Category();
        //category.setCategoryName(request.getCategoryName());
        //category.setDescription(request.getDescription());
        categoryService.add(request);
        //categoryRepository.save(category);
        return new ResponseEntity("Kategori eklendi", HttpStatus.CREATED);
    }*/

    @GetMapping("search")
    public List<Category> search(@RequestParam("name") String name) {
        // List<Category> categories = categoryRepository.searchNative(name);
        return null;
    }

    @GetMapping("getById")
    public Category getCategoryById(@RequestParam("id") int id) {

        //Category category = categoryRepository.findById(id).orElseThrow();
        return null;
    }
}
