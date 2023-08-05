package tech.rodolfoi.ecommercepublisher.builders;

import tech.rodolfoi.ecommercepublisher.dtos.Item;
import tech.rodolfoi.ecommercepublisher.dtos.Order;

import java.util.List;

public class OrderBuilder {
    private Order order;

    public OrderBuilder() {
        this.order = new Order();
    }

    public OrderBuilder withOrder(String order) {
        this.order.setOrder(order);
        return this;
    }

    public OrderBuilder withOrigin(String origin) {
        this.order.setOrigin(origin);
        return this;
    }

    public OrderBuilder withItems(List<Item> items) {
        this.order.setItems(items);
        return this;
    }

    public Order build() {
        return this.order;
    }
}
