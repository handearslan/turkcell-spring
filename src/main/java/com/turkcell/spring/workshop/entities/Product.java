package com.turkcell.spring.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(name="product_name")
    private String productName;  //varchar=string


    @ManyToMany
    @JoinColumn(name="supplier_id")
    private List<Supplier> supplier;


    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonIgnore
    private Category category;


    @Column(name="quantity_per_unit")
    private String quantityPerUnit;


    @Column(name="unit_price")
    private float unitPrice;


    @Column(name="units_in_stock")
    private short unitsInStock;


    @Column(name="units_on_order")
    private short unitsOnOrder;


    @Column(name="reorder_level")
    private short reorderLevel;


    @Column(name="discontinued") // istenmiyor
    private int discontinued;

    @Column(name="quantity_unit")
    private String quantityUnit;
}
