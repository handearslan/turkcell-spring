package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Short> {

}
