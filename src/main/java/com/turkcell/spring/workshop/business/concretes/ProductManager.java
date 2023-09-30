package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForUpdateDto;
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

    public List<ProductForListingIdDto> getById(int productID) {

        return productRepository.getForListingId(productID);
    }

    public void addProduct(ProductForAddDto request) {
        productWithSameNameShouldNotExist(request.getProductName());
        addProductUnitPriceControl(request);

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

    private void addProductUnitPriceControl(ProductForAddDto request) {
        // Product existingProduct = productRepository.findByUnitPrice(unitPrice);
        if (request.getUnitPrice() < 0) {
            throw new BusinessException("Ürün fiyatı 0'dan küçük olmamalıdır.");
        }
    }

    private void productWithSameNameShouldNotExist(String productName) {
        //  Product productWithSameName = productRepository.findByProductName(productName);
        if (productName == null || !productName.startsWith("HND")) {
            throw new BusinessException("Ürün adı 'HND' ile başlamalıdır.");
        }
    }

    @Override
    public void updateProduct(int productID, ProductForUpdateDto product) {
        updateProductNotFound(product.getProductID());

        Optional<Product> optionalProduct = productRepository.findById(productID);

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

    private void updateProductNotFound(int productID) {
        Product existingProduct = productRepository.findByProductID(productID);
        if (existingProduct == null) {
            throw new BusinessException("Güncellenecek ürün bulunamadı.");
        }
    }

    public void deleteProduct(int productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            productRepository.delete(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}







