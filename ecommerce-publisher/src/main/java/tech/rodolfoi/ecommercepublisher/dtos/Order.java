package tech.rodolfoi.ecommercepublisher.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import tech.rodolfoi.ecommercepublisher.builders.OrderBuilder;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;

@Data
public class Order {
    private String order;
    private String origin;
    @Setter(AccessLevel.NONE)
    private Double total;
    @Setter(AccessLevel.NONE)
    private String createdAt;
    private List<Item> items;

    public Order() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now.format(DateTimeFormatter.ISO_DATE_TIME);
    }

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
        return new OrderBuilder()
                .withOrder(orderInput.order())
                .withOrigin(orderInput.origin())
                .withItems(Item.fromItemInput(orderInput.items()))
                .build();
    }
}
