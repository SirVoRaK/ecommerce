package tech.rodolfoi.ecommercepublisher.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;

@Data
public class Order {
    private String order;
    private String origin;
    private Double total;
    private String createdAt;
    private List<Item> items;

    public void setItems(List<Item> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            BigDecimal cost = BigDecimal.valueOf(item.getCost());
            BigDecimal quantity = BigDecimal.valueOf(item.getQty());
            total = total.add(cost.multiply(quantity));
        }
        this.total = total.doubleValue();
        this.items = items;
    }

    public static Order fromOrderInput(OrderInput orderInput) {
        Order order = new Order();
        order.setOrder(orderInput.order());
        order.setOrigin(orderInput.origin());
        order.setItems(Item.fromItemInput(orderInput.items()));
        return order;
    }
}
