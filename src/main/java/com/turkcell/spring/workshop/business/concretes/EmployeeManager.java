package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.EmployeeService;
import com.turkcell.spring.workshop.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
