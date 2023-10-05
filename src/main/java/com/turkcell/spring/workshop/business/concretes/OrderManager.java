package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.OrderService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Customer;
import com.turkcell.spring.workshop.entities.Employee;
import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForUpdateDto;
import com.turkcell.spring.workshop.repositories.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;

    private final EmployeeRepository employeeRepository;

    private final CustomerRepository customerRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;




    public OrderManager(OrderRepository orderRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderForListingDto> getAll() {
        return orderRepository.getForListing();
    }

    @Override
    public List<OrderForListingIdDto> getById(int orderId) {
        return orderRepository.getForListingId(orderId);
    }

    @Override
    public void addOrder(OrderForAddDto request) {

        freightWithNumberBiggerThanTwentyOne(request);
        shipCityWithSameNameShouldNotExist(request);
        checkIfEmployeeExistById(request);
        Order order = new Order();
        order.setOrderDate(request.getOrderDate());
        order.setRequiredDate(request.getRequiredDate());
        order.setShipVia(request.getShipVia());
        order.setFreight(request.getFreight());
        order.setShipCity(request.getShipCity());
        order.setShipRegion(request.getShipRegion());
        order.setShipCountry(request.getShipCountry());

        /*OrderDetail orderDetail = new OrderDetail();
        orderDetail.getOrder().setOrderId(request2.getOrderId());
        orderDetail.getProduct().setProductID(request2.getProductID());
        orderDetail.setUnit_price(request2.getUnit_price());
        orderDetail.setQuantity(request2.getQuantity());
        orderDetail.setDiscount(request2.getDiscount());

        orderDetailsRepository.save(orderDetail);*/
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
        if (request.getShipCity() == null)
            throw new BusinessException("Şehir ismi boş olamaz.");
    }

    private void checkIfEmployeeExistById(OrderForAddDto request) {
        //Optional<Employee> employee = employeeRepository.findById(request.getEmployee_id());

        //if () {
        throw new BusinessException("Çalışan id değeri bulunamadı ");
        //}
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
            existingOrder.setOrderDate(order.getOrderDate());


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
    public void orderWithShippedDateGreaterThanOrderDate(LocalDate orderDate, LocalDate shippedDate) {
        int date = orderDate.compareTo(shippedDate);

        if (date > 0) {
            throw new BusinessException("şipariş tarihi kargo tarihinden sonra olamaz.");
        }
    }



    private void checkIfCustomerExistById(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new BusinessException("Müşteri id değeri bulunamadı ");
        }
    }
    private void checkIfEmployeeExistById(int employee_id) {
        Optional<Employee> employee = employeeRepository.findById(employee_id);
        if (employee == null) {
            throw new BusinessException("Çalışan id değeri bulunamadı ");
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

