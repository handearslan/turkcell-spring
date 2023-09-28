package com.turkcell.spring.workshop.business.concretes;


import com.turkcell.spring.workshop.business.abstracts.OrderService;
import com.turkcell.spring.workshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
