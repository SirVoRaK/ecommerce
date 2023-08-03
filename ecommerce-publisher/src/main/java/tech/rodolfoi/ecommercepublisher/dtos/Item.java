package tech.rodolfoi.ecommercepublisher.dtos;

import java.util.*;
import lombok.Data;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.ItemInput;

@Data
public class Item {
    private String name;
    private String image;
    private int qty;
    private Double cost;
    private String currency;

    public static Item fromItemInput(ItemInput itemInput) {
        Item item = new Item();
        item.setName(itemInput.name());
        item.setImage(itemInput.image());
        item.setQty(itemInput.qty());
        item.setCost(itemInput.cost());
        item.setCurrency(itemInput.currency());
        return item;
    }

    public static List<Item> fromItemInput(List<ItemInput> itemInput) {
        List<Item> items = new ArrayList<>();
        for (ItemInput item : itemInput)
            items.add(fromItemInput(item));

        return items;
    }
}

