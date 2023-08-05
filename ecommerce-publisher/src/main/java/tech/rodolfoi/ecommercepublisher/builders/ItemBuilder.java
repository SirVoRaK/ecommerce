package tech.rodolfoi.ecommercepublisher.builders;

import tech.rodolfoi.ecommercepublisher.dtos.Item;

public class ItemBuilder {
    Item item;

    public ItemBuilder() {
        item = new Item();
    }

    public ItemBuilder withName(String name) {
        item.setName(name);
        return this;
    }

    public ItemBuilder withImage(String image) {
        item.setImage(image);
        return this;
    }

    public ItemBuilder withQty(int qty) {
        item.setQty(qty);
        return this;
    }

    public ItemBuilder withCost(Double cost) {
        item.setCost(cost);
        return this;
    }

    public ItemBuilder withCurrency(String currency) {
        item.setCurrency(currency);
        return this;
    }

    public Item build() {
        return item;
    }
}
