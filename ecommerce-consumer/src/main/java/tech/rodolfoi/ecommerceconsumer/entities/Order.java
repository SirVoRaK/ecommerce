package tech.rodolfoi.ecommerceconsumer.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private String order;
    private String origin;
    private Double total;
    private String createdAt;
    private Item[] items;
}
