package com.turkcell.spring.workshop.business;

import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto;
import com.turkcell.spring.workshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public List<ProductForListingDto> getAll() {
        return productRepository.getForListing();
    }


    public List<ProductForListingIdDto> getById(int productId) {

        return productRepository.getForListingId(productId);
    }

  /*  public void addProduct(ProductForAddDto request) {
        Product product = new Product();
        product.setProductName(request.getproductNamee());
        product.setQuantityPerUnit(request.getQuantityPerUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setDiscontinued(0);
    }*/

}




