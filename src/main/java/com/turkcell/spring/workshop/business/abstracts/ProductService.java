package com.turkcell.spring.workshop.business.abstracts;

import com.turkcell.spring.workshop.entities.dtos.Product.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForUpdateDto;

import java.util.List;

public interface ProductService {

    List<ProductForListingDto> getAll();

    List<ProductForListingIdDto> getById(int productId);

    void addProduct(ProductForAddDto request);

    float getByUnitPrice(int productId);

    short getUnitInStock(int productId);

    void setUnitInStock(short quantity, int productId);

    void updateProduct(int productId, ProductForUpdateDto product);

    void deleteProduct(int productId);

}