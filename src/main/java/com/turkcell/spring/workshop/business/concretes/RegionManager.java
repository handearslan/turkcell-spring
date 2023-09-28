package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.RegionService;
import com.turkcell.spring.workshop.repositories.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class RegionManager implements RegionService {
    private final RegionRepository regionRepository;

    public RegionManager(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
}
