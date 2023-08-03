package tech.rodolfoi.ecommercepublisher.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import tech.rodolfoi.ecommercepublisher.dtos.Order;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;
import tech.rodolfoi.ecommercepublisher.publishers.OrderPublisher;
import tech.rodolfoi.ecommercepublisher.services.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderPublisher orderPublisher;

    @Override
    public void create(OrderInput orderInput) {
        Order order = Order.fromOrderInput(orderInput);
        Gson gson = new Gson();
        System.out.println(gson.toJson(order));
        orderPublisher.publishMessage(order);
    }
}
