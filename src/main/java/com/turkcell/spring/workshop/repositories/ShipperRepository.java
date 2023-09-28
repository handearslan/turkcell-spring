package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shipper ,Short> {

}
