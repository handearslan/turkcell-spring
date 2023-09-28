package com.turkcell.spring.workshop.business;

import com.turkcell.spring.workshop.entities.dtos.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto;

import java.util.List;

public interface ProductService {
    //  void add(Product product);
    // void update(int id, Product product);
    //  void delete(int id);
    List<ProductForListingDto> getAll();

    List<ProductForListingIdDto> getById(int productId);
   // void addProduct(ProductForAddDto request);


}
