package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.entities.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("home")
//http://localhost:8080/home
//http://localhost:8080/home/index
//http://localhost:8080/home/categories

public class HomeController {

    List<Product> productsList = new ArrayList<>();

    @GetMapping("") //get isteği
    public String get(){
        return "Merhaba Turkcell";
    }

    @PostMapping("")  //post isteği
    public String getPost(){
        return "Merhaba Turkcell Post";
    }

    //http://localhost:8080/home/index
    @GetMapping("index")
    public String get2(){
        return "Merhaba Turkcell 2";
    }

    //http://localhost:8080/home/products
    @GetMapping("products")
    public List<Product> getProducts(){

        /*Product product1 = new Product();
        product1.setId(1);
        product1.setName("aaa");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("bbb");

        productsList.add(product1);
        productsList.add(product2);*/

        return productsList;
    }

    //Query String -> localhost:8081/home/getById?id=1&name=deneme
    // Route -> localhost:8081/home/getById/1/deneme
    @GetMapping("getById")
    public Product getById(@RequestParam("id") short id){ //int id, String name
        Product product = new Product();
        product.setProductID(id);
        product.setProductName("ccc");
        return product;
    }

    //Body -> localhost:8081/product
    @PostMapping("product")
    public ResponseEntity<String> addProduct(@RequestBody Product product)
    {
        if(product.getProductID()<=0){
            //return "Eklenecek ürünün id si sıfırdan büyük olmalı";
            return new ResponseEntity<>("Eklenecek ürünün id si sıfırdan büyük olmalı", HttpStatus.BAD_REQUEST);
        }

        productsList.add(product);


        //2xx -> ok
        //4xx -> error

        HttpHeaders headers = new HttpHeaders();
        headers.add("Product-Name",product.getProductName());
        //return product.getName() + " ürünü eklendi...";
        return new ResponseEntity<>(product.getProductName() + " ürünü eklendi...",headers,HttpStatus.CREATED);
    }






}
