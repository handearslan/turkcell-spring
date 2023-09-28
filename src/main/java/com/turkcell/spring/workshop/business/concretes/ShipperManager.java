package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.ShipperService;
import com.turkcell.spring.workshop.repositories.ShipperRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipperManager implements ShipperService {
    private final ShipperRepository shipperRepository;

    public ShipperManager(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }
}
