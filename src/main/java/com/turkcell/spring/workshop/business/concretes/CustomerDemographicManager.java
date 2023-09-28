package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.CustomerDemographicService;
import com.turkcell.spring.workshop.repositories.CustomerDemographicRepository;

public class CustomerDemographicManager implements CustomerDemographicService {
    private final CustomerDemographicRepository customerDemographicRepository;

    public CustomerDemographicManager(CustomerDemographicRepository customerDemographicRepository) {
        this.customerDemographicRepository = customerDemographicRepository;
    }
}
