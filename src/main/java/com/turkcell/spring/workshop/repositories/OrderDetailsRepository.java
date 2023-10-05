package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {


}
