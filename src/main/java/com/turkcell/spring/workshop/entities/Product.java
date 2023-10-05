package com.turkcell.spring.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(name = "product_name")
    private String productName;  //varchar=string

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "units_in_stock")
    private short unitsInStock;

    @Column(name = "units_on_order")
    private short unitsOnOrder;

    @Column(name = "reorder_level")
    private short reorderLevel;

    @Column(name = "discontinued") // istenmiyor
    private int discontinued;

    @Column(name = "quantity_unit")
    private String quantityUnit;
}
