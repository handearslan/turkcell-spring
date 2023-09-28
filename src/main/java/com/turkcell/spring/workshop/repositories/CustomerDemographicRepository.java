package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.CustomerDemographic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDemographicRepository extends JpaRepository<CustomerDemographic , String> {

}
