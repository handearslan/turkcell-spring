package com.turkcell.spring.workshop.business.abstracts;

import com.turkcell.spring.workshop.entities.dtos.Order.OrderForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForUpdateDto;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface OrderService {

    List<OrderForListingDto> getAll();

    List<OrderForListingIdDto> getById(int orderId);

    void addOrder(OrderForAddDto request);

    void updateOrder(int orderId, OrderForUpdateDto order);

    void deleteOrder(int orderId);

}
