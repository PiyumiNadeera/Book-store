package com.onlinebookstore.book_store.service;

import com.onlinebookstore.book_store.entity.Order;
import com.onlinebookstore.book_store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void addOrder(Order order){
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id,Order order){
        Order existingOrder = orderRepository.findById(id).orElseThrow(null);

        if (existingOrder != null) {
            existingOrder.setPrice(order.getPrice());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setCustomerId(order.getCustomerId());

            orderRepository.save(existingOrder);
        }

    }

    @Override
    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(null);

        if (order != null) {
            orderRepository.deleteById(id);
        }
    }
}
