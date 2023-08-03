package tech.rodolfoi.ecommerceconsumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import tech.rodolfoi.ecommerceconsumer.entities.Order;
import tech.rodolfoi.ecommerceconsumer.services.interfaces.OrderService;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @QueryMapping
    public Iterable<Order> placedOrders() {
        return this.orderService.findAll();
    }
}
