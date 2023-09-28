package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.ProductService;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8081/products/add
@RestController
@RequestMapping("products")
public class ProductController {

    public ProductController(ProductService productServices) {
        this.productServices = productServices;
    }

    private final ProductService productServices;


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
 /*   @PostMapping("add")
    public ResponseEntity add(@RequestBody Product product) {
        productServices.add(product);
        return new ResponseEntity<>(product.getProductName() + " ürünü eklendi...", HttpStatus.CREATED);
    }*/

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
