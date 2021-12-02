package com.mephi.hotel.service;

import com.mephi.hotel.model.Order;
import com.mephi.hotel.model.Payment;
import com.mephi.hotel.repository.OrderRepository;
import com.mephi.hotel.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Stream<Order> getAllOrder() {
        return orderRepository.findAll().stream();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}
