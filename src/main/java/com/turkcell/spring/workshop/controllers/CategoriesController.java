package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.CategoryService;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.dtos.CategoryForAddDto;
import com.turkcell.spring.workshop.entities.dtos.CategoryForListingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//http://localhost:8081/categories
@RestController
@RequestMapping("categories")
public class CategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping("getByName")
    public List<Category> getCategoriesByName(@RequestParam("name") String name){
        // List<Category> categories = categoryRepository.findByCategoryNameContaining(name);
        return  null;
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody CategoryForAddDto request){
        // Manual Mapleme
        // Auto Mapper => ModelMapper
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        //categoryRepository.save(category);
        return new ResponseEntity("Kategori eklendi", HttpStatus.CREATED);
    }

    @GetMapping("search")
    public List<Category> search(@RequestParam("name") String name){
        // List<Category> categories = categoryRepository.searchNative(name);
        return  null;
    }

    @GetMapping("getById")
    public Category getCategoryById(@RequestParam("id") int id){

        //Category category = categoryRepository.findById(id).orElseThrow();
        return null;

    }
    @GetMapping
    public List<CategoryForListingDto> getCategories(){

        List<CategoryForListingDto> categoriesInDb = categoryService.getAll();
        return  categoriesInDb;
    }

}
