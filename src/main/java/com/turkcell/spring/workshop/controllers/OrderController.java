package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//http://localhost:8081/orders/add
@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getOrders(){

        List<Order> orderInDb = orderRepository.findAll();
        return  orderInDb;
    }

    @GetMapping("getById") //calısmıyor
    public Order getOrderById(@RequestParam("id") int id){

        Order order = orderRepository.findById(id).orElseThrow();
        return order;
    }
}
