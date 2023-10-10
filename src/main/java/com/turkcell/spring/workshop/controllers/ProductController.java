package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.core.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8081/products/add
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productServices;
    private final MessageSource messageSource;

    @GetMapping
    public List<ProductForListingDto> getProduct() {

        List<ProductForListingDto> productInDb = productServices.getAll();
        return productInDb;
    }

    @GetMapping("getById/{productId}")
    public List<ProductForListingIdDto> getById(@PathVariable("productId") int productId) {
        return productServices.getById(productId);
    }

    /*  ürün eklenmesi ekleme yaparken kullanıcıdan id alanı
      istenmemeli ve discontinued istenmeyip otomatik 0 olarak tanımlanmalı*/
    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid ProductForAddDto product) {
        productServices.addProduct(product);

        return new ResponseEntity(messageSource.getMessage("ProductAdd",new Object[] {product.getProductName()},LocaleContextHolder.getLocale()) , HttpStatus.CREATED);

        //return new ResponseEntity(product.getProductName() + " adlı ürün eklendi.", HttpStatus.CREATED);

    }

    @PutMapping("updateProduct/{productId}")
    public ResponseEntity updateProduct(@PathVariable("productId") int productId, @RequestBody @Valid ProductForUpdateDto product) {
        productServices.updateProduct(productId, product);

        return new ResponseEntity(messageSource.getMessage("ProductUpdate",new Object[] {product.getProductName()},LocaleContextHolder.getLocale()) , HttpStatus.OK);

        //return new ResponseEntity(product.getProductName() + "Ürün güncellendi", HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity deleteProduct(@PathVariable("productId") int productId) {
        productServices.deleteProduct(productId);

        return new ResponseEntity(messageSource.getMessage("ProductDelete",null,LocaleContextHolder.getLocale()) , HttpStatus.OK);

        //return new ResponseEntity("Ürün silindi", HttpStatus.OK);
    }

    @GetMapping("getByUnitPrice")
    public List<Product> getProductById(@RequestParam("unitPrice") double unitPrice) {

        // List<Product> product = productRepository.findByUnitPrice(unitPrice);
        return null;
    }

    @GetMapping("getByUnitsInStock")
    public List<Product> getUnitsInStock(@RequestParam("UnitsInStock") short UnitsInStock) {

        //List<Product> product = productRepository.UnitsInStockGreaterThanEqual(UnitsInStock);
        return null;
    }

    @GetMapping("getByQuantityUnit")
    public List<Product> getQuantityUnit() {
        //List<Product> product = productRepository.findByQuantityUnitIsNotNull();
        return null;
    }

    @GetMapping("getByQuantityUnitNull")
    public List<Product> getQuantityUnitNull() {

        //List<Product> product = productRepository.findByQuantityUnitIsNull();
        return null;
    }

    @GetMapping("search")
    public List<Product> getSearch(@RequestParam("a") float a, @RequestParam("b") float b) {
        // List<Product> product = productRepository.search(a,b);
        return null;
    }

    @GetMapping("discontinued")
    public List<Product> getdiscontinued() {
        // List<Product> product = productRepository.discontinued();
        return null;
    }

    @GetMapping("thenAvg")
    public List<Product> getThenAvg() {
        // List<Product> product = productRepository.thenAvg();
        return null;
    }

    @GetMapping("searchNative")
    public List<Product> searchNative() {
        //List<Product> product = productRepository.searchNative();
        return null;
    }

    @GetMapping("searchNativeAls")
    public List<Product> searchNativeAls(@RequestParam("als") String als) {
        // List<Product> product = productRepository.searchNativeAls(als);
        return null;
    }
}

