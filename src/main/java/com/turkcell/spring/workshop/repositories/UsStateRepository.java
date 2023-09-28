package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.UsState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsStateRepository extends JpaRepository<UsState , Short> {

}
