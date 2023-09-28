package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.UsStateService;
import com.turkcell.spring.workshop.repositories.UsStateRepository;
import org.springframework.stereotype.Service;

@Service
public class UsStateManager implements UsStateService {
    private final UsStateRepository usStateRepository;

    public UsStateManager(UsStateRepository usStateRepository) {
        this.usStateRepository = usStateRepository;
    }
}

