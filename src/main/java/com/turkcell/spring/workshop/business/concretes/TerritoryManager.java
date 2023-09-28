package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.TerritoryService;
import com.turkcell.spring.workshop.repositories.TerritoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TerritoryManager implements TerritoryService {
    private final TerritoryRepository territoryRepository;

    public TerritoryManager(TerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }
}
