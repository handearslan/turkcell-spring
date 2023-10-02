package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto" +
            "(o.orderId,o.orderDate,o.requiredDate,o.shipVia,o.freight,o.shipName,o.shipCity,o.shipRegion,o.shipCountry) FROM Order o")
    List<OrderForListingDto> getForListing();

    @Query(value = "SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto(o.orderId,o.orderDate,o.requiredDate,o.shipVia,o.freight,o.shipName,o.shipCity,o.shipRegion,o.shipCountry)" +
            " FROM Order o WHERE o.orderId = :orderId")
    List<OrderForListingIdDto> getForListingId(int orderId);

    Order findByShipName(String shipName);

    Order findByShipCity(String shipCity);

    Order findByFreight(Float freight);
}
