package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {

    @Id()
    @Column(name = "order_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short orderDetailsId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "quantity")
    private short quantity;

    @Column(name = "discount")
    private float discount;
}