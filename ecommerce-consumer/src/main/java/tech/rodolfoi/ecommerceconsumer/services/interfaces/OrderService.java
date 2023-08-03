package tech.rodolfoi.ecommerceconsumer.services.interfaces;

import java.util.List;

import tech.rodolfoi.ecommerceconsumer.entities.Order;

public interface OrderService {
    void save(Order order);
    List<Order> findAll();
}
