package com.turkcell.spring.workshop.controllers;

import com.turkcell.spring.workshop.business.abstracts.OrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order_details")
public class OrderDetailsController {

    private final OrderDetailService orderDetailService;


    public OrderDetailsController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping()
    public List<Object> getOrderDetails() {
        return orderDetailService.getForListing();
    }
}