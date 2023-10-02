package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.OrderService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForUpdateDto;
import com.turkcell.spring.workshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderForListingDto> getAll() {    return orderRepository.getForListing();  }

    @Override
    public List<OrderForListingIdDto> getById(int orderId) {
        return orderRepository.getForListingId(orderId);
    }

    @Override
    public void addOrder(OrderForAddDto request) {

        freightWithNumberBiggerThanTwentyOne(request);
        shipCityWithSameNameShouldNotExist(request);

        Order order = new Order();
        order.setOrderDate((Date) request.getOrderDate());
        order.setRequiredDate((Date) request.getRequiredDate());
        order.setShipVia(request.getShipVia());
        order.setFreight(request.getFreight());
        order.setShipCity(request.getShipCity());
        order.setShipRegion(request.getShipRegion());
        order.setShipCountry(request.getShipCountry());

        orderRepository.save(order);
    }

    private void freightWithNumberBiggerThanTwentyOne(OrderForAddDto request) {
        // Order orderWithBiggerThan = orderRepository.findByShipCountry(shipCountry);
        if (request.getFreight() <= 21.5) {
            throw new BusinessException("Freight 21.5'dan büyük olmalıdır.");
        }
    }

    private void shipCityWithSameNameShouldNotExist(OrderForAddDto request) {
        // Order orderWithSameName = orderRepository.findByShipCity(shipCity);
        if (request.getShipCity() != null)
            throw new BusinessException("Şehir ismi boş olamaz.");
    }

    @Override
    public void updateOrder(int orderId, OrderForUpdateDto order) {
        orderWithSameNameShouldNotExist(order.getShipName());

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            // Güncelleme isteğindeki verileri kullanarak mevcut ürünü güncelle
            existingOrder.setShipName(order.getShipName());
            existingOrder.setShipRegion(order.getShipRegion());
            existingOrder.setShipCountry(order.getShipCountry());
            existingOrder.setOrderDate((Date) order.getOrderDate());


            // Güncellenen ürünü kaydet
            orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    private void orderWithSameNameShouldNotExist(String shipName) {
        Order orderWithSameName = orderRepository.findByShipName(shipName);

        if (orderWithSameName != null && orderWithSameName.getShipName().length() >= 20) {
            throw new BusinessException("Sipariş ismi 20 karakterden uzun olamaz.");
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            orderRepository.delete(existingOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}

