package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerritoryRepository extends JpaRepository<Territory , String> {

}
