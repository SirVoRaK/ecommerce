package tech.rodolfoi.ecommercepublisher.dtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.builders.ItemBuilder;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.ItemInput;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderTests {
    @Test
    public void setItems_ShouldCalculateTotal() {
        // Arrange
        Item fakeItem1 = new ItemBuilder()
                .withName("fake item 1")
                .withImage("fake image 1")
                .withQty(1)
                .withCost(10.0)
                .withCurrency("BRL")
                .build();
        Item fakeItem2 = new ItemBuilder()
                .withName("fake item 2")
                .withImage("fake image 2")
                .withQty(2)
                .withCost(20.0)
                .withCurrency("BRL")
                .build();
        List<Item> fakeItems = List.of(fakeItem1, fakeItem2);

        double expectedTotal = 50.0;

        // Act
        Order order = new Order();
        order.setItems(fakeItems);

        // Assert
        assertEquals(expectedTotal, order.getTotal(), 0.0);
    }

    @Test
    public void fromOrderInput_ShouldCreateAnOrder() {
        // Arrange
        ItemInput fakeItemInput = new ItemInput("fake item", "fake image", 1, 10.0, "BRL");
        OrderInput fakeOrderInput = new OrderInput("fake order", "fake origin", List.of(fakeItemInput));

        // Act
        Order order = Order.fromOrderInput(fakeOrderInput);

        // Assert
        assertEquals(fakeOrderInput.order(), order.getOrder());
        assertEquals(fakeOrderInput.origin(), order.getOrigin());
        assertEquals(fakeOrderInput.items().size(), order.getItems().size());
    }

    @Test
    public void constructor_ShouldSetCreatedAt() {
        // Act
        Order order = new Order();

        // Assert
        assertNotEquals(null, order.getCreatedAt());
        assertNotEquals("", order.getCreatedAt());
    }
}
