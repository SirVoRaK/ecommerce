package tech.rodolfoi.ecommerceconsumer.entities;

import lombok.Data;

@Data
public class Item {
    private String name;
    private String image;
    private Integer qty;
    private Double cost;
    private String currency;
}
