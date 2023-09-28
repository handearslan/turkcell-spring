package com.turkcell.spring.workshop.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToMany()
    @JoinColumn(name = "customer_id")
    private List<Customer> customer;

    @ManyToMany()
    @JoinColumn(name = "employee_id")
    private List<Employee> employee;

    @Column(name = "order_date")
    private java.sql.Date orderDate;

    @Column(name = "required_date")
    private java.sql.Date requiredDate;

    @Column(name = "shipped_date")
    private java.sql.Date shippedDate;

    @Column(name = "ship_via")
    private short shipVia;

    @Column(name = "freight")
    private float freight;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_region")
    private String shipRegion;

    @Column(name = "ship_postal_code")
    private String shipPostalCode;

    @Column(name = "ship_country")
    private String shipCountry;
}
