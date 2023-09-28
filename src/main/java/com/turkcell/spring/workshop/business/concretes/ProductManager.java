package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForUpdateDto;
import com.turkcell.spring.workshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public void addProduct(ProductForAddDto request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setQuantityPerUnit(request.getQuantityPerUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setUnitsInStock(request.getUnitsInStock());
        product.setUnitsOnOrder(request.getUnitsOnOrder());
        product.setReorderLevel(request.getReorderLevel());
        product.setQuantityUnit(request.getQuantityUnit());
        product.setDiscontinued(0);
        productRepository.save(product);
    }

  /*  @Override
    public void updateProduct(int productId, ProductForUpdateDto product) {

        if (productId == product.getProductId()) {
            product.setProductName(product.getProductName());
            product.setUnitPrice(product.getUnitPrice());
            product.getQuantityPerUnit(product.getQuantityPerUnit());
            product.unit(product.getUnitPrice());
            product.setUnitPrice(product.getUnitPrice());
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }*/

    @Override
    public void updateProduct(int productId, ProductForUpdateDto product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Güncelleme isteğindeki verileri kullanarak mevcut ürünü güncelle
            existingProduct.setProductName(product.getProductName());
            existingProduct.setUnitPrice(product.getUnitPrice());

            // Güncellenen ürünü kaydet
            productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    public void deleteProduct(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            productRepository.delete(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }




}




