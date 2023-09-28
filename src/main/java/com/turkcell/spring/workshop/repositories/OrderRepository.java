package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
