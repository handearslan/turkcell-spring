package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.OrderDetailService;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.OrderDetail;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.OrderDetails.OrderDetailsForAddto;
import com.turkcell.spring.workshop.repositories.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailManager implements OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;

    private final ProductService productService;

    public OrderDetailManager(OrderDetailsRepository orderDetailsRepository, ProductService productService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.productService = productService;
    }

    @Override
    public void addItemsToOrder(Order order, List<OrderDetailsForAddto> items) {

        for (OrderDetailsForAddto item : items) {
            item.setQuantity(RequestedProductCannotExceedTheNumberOfProductsInStock(item.getQuantity(), item.getProductId()));
            productService.setUnitInStock(item.getQuantity(), item.getProductId());

            OrderDetail od = OrderDetail.builder()
                    .product(Product.builder().productID(item.getProductId()).build())
                    .order(Order.builder().orderId(order.getOrderId()).build())
                    .discount(0)
                    .quantity(item.getQuantity())
                    .unitPrice(productService.getByUnitPrice(item.getProductId())) // TODO: Find product and get unit price from productService
                    .build();
            orderDetailsRepository.save(od);
        }
    }

    @Override
    public List<Object> getForListing() {
        return orderDetailsRepository.getForListing();
    }

    //Eğer ilgili üründen talep edilen adet kadar yoksa adet verilebilecek max. stok olarak güncellenmelidir.
    private short RequestedProductCannotExceedTheNumberOfProductsInStock(short quantity, int productID) {

        short unitsInStock = productService.getUnitInStock(productID);
        if (quantity > unitsInStock) {
            //   productService.setUnitInStock(unitsInStock,productID);
            return unitsInStock;
        }
        //  productService.setUnitInStock(quantity,productID);
        return quantity;
    }
}



