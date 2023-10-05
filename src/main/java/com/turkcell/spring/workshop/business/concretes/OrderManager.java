package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.OrderDetailService;
import com.turkcell.spring.workshop.business.abstracts.OrderService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.Customer;
import com.turkcell.spring.workshop.entities.Employee;
import com.turkcell.spring.workshop.entities.Order;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Order.OrderForUpdateDto;
import com.turkcell.spring.workshop.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailService orderDetailService;


    public OrderManager(OrderRepository orderRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository, OrderDetailService orderDetailService) {

        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;

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
    @Transactional // metotun tamamen başarılı bir şekilde bitmesini bekleyip değişiklikleri o şekilde pushlayan metot
    public void addOrder(OrderForAddDto request) {

        // Order'ı dbye kaydet, orderin bir id'si oluşsun..
        // oluşan id'yi ve itemları orderdetail service gönder o da bu idye detay eklemelerini yapsın..

        freightWithNumberBiggerThanTwentyOne(request);
        checkIfEmployeeExistById(request);
        shipCityWithSameNameShouldNotExist(request);

        Order order = Order.builder()
                .customer(Customer.builder().customerID(request.getCustomerID()).build())
                .orderDate(LocalDate.now())
                .employee(Employee.builder().employeeId(request.getEmployeeId()).build())
                .requiredDate(request.getRequiredDate())
                .shipAddress(request.getShipAddress())
                .shipCity(request.getShipCity())
                .shipName(request.getShipName())
                .shipRegion(request.getShipRegion())
                .build();

        order = orderRepository.save(order);  // gönderen hesaptan parayı düş

        // bu satırdan sonra order'ın id alanı set edilmiş..
        orderDetailService.addItemsToOrder(order, request.getItems()); // gönderilen hesaba parayı göndermek

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

        Order orderToUpdate = returnOrderByIdIfExists(orderId);

        // Güncelleme isteğindeki verileri kullanarak mevcut ürünü güncelle
        orderToUpdate.setShipName(order.getShipName());
        orderToUpdate.setShipRegion(order.getShipRegion());
        orderToUpdate.setShipCountry(order.getShipCountry());
        orderToUpdate.setOrderDate(order.getOrderDate());

        // Güncellenen ürünü kaydet
        orderRepository.save(orderToUpdate);
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

        Order orderToDelete = returnOrderByIdIfExists(orderId);

        orderRepository.delete(orderToDelete);
    }

    private Order returnOrderByIdIfExists(int orderId) {
        Order orderToDelete = orderRepository.findById(orderId).orElse(null);
        if (orderToDelete == null)
            throw new BusinessException("Böyle bir sipariş bulunamadı.");
        return orderToDelete;
    }
}

 /*   private void checkIfCustomerExistById(String customerId) {
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
    }*/

/* public void orderWithShippedDateGreaterThanOrderDate(LocalDate orderDate, LocalDate shippedDate) {
        int date = orderDate.compareTo(shippedDate);

        if (date > 0) {
            throw new BusinessException("şipariş tarihi kargo tarihinden sonra olamaz.");
        }
    }*/

