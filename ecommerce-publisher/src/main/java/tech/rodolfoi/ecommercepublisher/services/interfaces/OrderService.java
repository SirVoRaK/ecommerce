package tech.rodolfoi.ecommercepublisher.services.interfaces;

import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;

public interface OrderService {
    void create(OrderInput orderInput);
}
