package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer_demographics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDemographic {
    @Id
    @Column(name = "customer_type_id")
    private String customerTypeId;

    @OneToMany(mappedBy = "customerDemographic")
    private List<CustomerCustomerDemo> customerCustomerDemos;

    @Column(name = "customer_desc")
    private String customerDesc;
}