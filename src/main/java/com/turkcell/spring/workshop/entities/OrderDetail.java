package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @ManyToOne()
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @Column(name="order_details_id")
    private int orderDetailsId;

    @Column(name = "unit_price")
    private float unit_price;

    @Column(name = "quantity")
    private short quantity;

    @Column(name = "discount")
    private float discount;
}
