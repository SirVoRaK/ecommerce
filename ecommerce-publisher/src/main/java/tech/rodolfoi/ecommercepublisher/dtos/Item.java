package tech.rodolfoi.ecommercepublisher.dtos;

import java.util.*;
import lombok.Data;
import tech.rodolfoi.ecommercepublisher.builders.ItemBuilder;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.ItemInput;

@Data
public class Item {
    private String name;
    private String image;
    private int qty;
    private Double cost;
    private String currency;

    public static Item fromItemInput(ItemInput itemInput) {
        return new ItemBuilder()
                .withName(itemInput.name())
                .withImage(itemInput.image())
                .withQty(itemInput.qty())
                .withCost(itemInput.cost())
                .withCurrency(itemInput.currency())
                .build();
    }

    public static List<Item> fromItemInput(List<ItemInput> itemInput) {
        List<Item> items = new ArrayList<>();
        for (ItemInput item : itemInput)
            items.add(fromItemInput(item));

        return items;
    }
}

