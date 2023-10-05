package com.turkcell.spring.workshop.business.abstracts;

import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.dtos.Order_Details.OrderDetailsForAddto;

import java.util.List;


public interface OrderDetailService {
    void addItemsToOrder(Order order, List<OrderDetailsForAddto> items);
}
