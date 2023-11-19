package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}