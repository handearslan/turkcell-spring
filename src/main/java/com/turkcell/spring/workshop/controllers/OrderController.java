package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.abstracts.OrderService;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8081/orders/add
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;
    private final MessageSource messageSource;

    /*public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }*/

    @GetMapping()
    public List<OrderForListingDto> getOrders() {
        List<OrderForListingDto> ordersInDb = orderService.getAll();
        return ordersInDb;
    }

    @GetMapping("getById/{orderId}")
    public List<OrderForListingIdDto> getById(@PathVariable("orderId") int orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid OrderForAddDto order) {
        orderService.addOrder(order);
        return new ResponseEntity(messageSource.getMessage("OrderAdded", null , LocaleContextHolder.getLocale()), HttpStatus.CREATED);
    }

    @PutMapping("updateOrder/{orderId}")
    public ResponseEntity updateOrder(@PathVariable("orderId") int orderId, @RequestBody @Valid OrderForUpdateDto order) {
        orderService.updateOrder(orderId, order);
        return new ResponseEntity(messageSource.getMessage("OrderUpdate", null , LocaleContextHolder.getLocale()), HttpStatus.OK);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity(messageSource.getMessage("orderDeleted",new Object[] {orderId}, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }
}

   /* @GetMapping
    public List<Order> getOrders(){

        List<Order> orderInDb = orderRepository.findAll();
        return  orderInDb;
    }

    @GetMapping("getById")
    public Order getOrderById(@RequestParam("id") int id){

        Order order = orderRepository.findById(id).orElseThrow();
        return order;
    }*/
