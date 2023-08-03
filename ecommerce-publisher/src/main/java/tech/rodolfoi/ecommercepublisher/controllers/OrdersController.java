package tech.rodolfoi.ecommercepublisher.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import tech.rodolfoi.ecommercepublisher.constants.Messages;
import tech.rodolfoi.ecommercepublisher.dtos.Order;
import tech.rodolfoi.ecommercepublisher.dtos.ResultResponse;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;
import tech.rodolfoi.ecommercepublisher.services.interfaces.OrderService;

@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @QueryMapping
    public Iterable<Order> orders() {
        return new ArrayList<>();
    }

    @MutationMapping
    public ResultResponse publishPlacedOrderMessage(@Argument OrderInput order) {
        try {
            orderService.create(order);
            return new ResultResponse(true, Messages.ORDER_CREATED);
        } catch (Exception e) {
            return new ResultResponse(false, Messages.ORDER_CREATION_FAILED);
        }
    }
}
