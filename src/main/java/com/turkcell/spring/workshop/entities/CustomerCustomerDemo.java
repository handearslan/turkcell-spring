package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_customer_demo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCustomerDemo {

    @EmbeddedId
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "customer_type_id")
    private CustomerDemographic customerDemographic;
}