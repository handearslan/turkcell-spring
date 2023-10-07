package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Short> {

    @Query(value = "SELECT o.order_id,p.product_name FROM orders o" +
            " JOIN order_details od on(o.order_id=od.order_id) Join products p on(od.product_id=p.product_id);", nativeQuery = true)
        List<Object> getForListing();


}
