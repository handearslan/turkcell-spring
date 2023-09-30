package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shippers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipper_id")
    private short shipperId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone")
    private String phone;
}
