package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Column(name = "customer_id")
    @Id()
    private String customerID;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<CustomerCustomerDemo> customerCustomerDemos;

    @Column(name = "company_name")
    private String companyName;  //smallint

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_title")
    private String contactTitle;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;
}