package com.turkcell.spring.workshop.business.concretes;


import com.turkcell.spring.workshop.business.abstracts.SupplierService;
import com.turkcell.spring.workshop.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierManager implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierManager(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
}
