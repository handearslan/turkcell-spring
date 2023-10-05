package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Customer;
import com.turkcell.spring.workshop.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
