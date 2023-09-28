package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Short> {

}
