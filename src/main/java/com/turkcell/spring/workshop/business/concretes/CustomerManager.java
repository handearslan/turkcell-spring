package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.CustomerService;
import com.turkcell.spring.workshop.repositories.CustomerRepository;
import org.springframework.stereotype.Service;


@Service
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
