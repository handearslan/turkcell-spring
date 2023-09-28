package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

}
