package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.OrderDetailService;

import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.OrderDetail;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.Order_Details.OrderDetailsForAddto;
import com.turkcell.spring.workshop.repositories.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailManager implements OrderDetailService{
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailManager(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }


    @Override
    public void addItemsToOrder(Order order, List<OrderDetailsForAddto> items) {
        for (OrderDetailsForAddto item: items) {
            OrderDetail od = OrderDetail.builder()
                    .product(Product.builder().productID(item.getProductId()).build())
                    .order(Order.builder().orderId(order.getOrderId()).build())
                    .discount(0)
                    .quantity(item.getQuantity())
                    .unitPrice(0) // TODO: Find product and get unit price from productService
                    .build();
            orderDetailsRepository.save(od);
        }
    }
}
