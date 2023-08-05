package tech.rodolfoi.ecommercepublisher.builders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.dtos.Item;
import tech.rodolfoi.ecommercepublisher.dtos.Order;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderBuilderTests {
    private OrderBuilder orderBuilder;

    @Before
    public void setUp() {
        orderBuilder = new OrderBuilder();
    }

    @Test
    public void withOrder_ShouldSetTheOrder() {
        // Arrange
        String fakeOrder = "fake order";

        // Act
        Order order = orderBuilder
                .withOrder(fakeOrder)
                .build();

        // Assert
        assertEquals(fakeOrder, order.getOrder());
    }

    @Test
    public void withOrigin_ShouldSetTheOrigin() {
        // Arrange
        String fakeOrigin = "fake origin";

        // Act
        Order order = orderBuilder
                .withOrigin(fakeOrigin)
                .build();

        // Assert
        assertEquals(fakeOrigin, order.getOrigin());
    }

    @Test
    public void withItems_ShouldSetTheItems() {
        // Arrange
        Item fakeItem = new ItemBuilder()
                .withName("fake name")
                .withQty(1)
                .withCost(1.0)
                .withImage("fake image")
                .withCurrency("fake currency")
                .build();

        // Act
        Order order = orderBuilder
                .withItems(List.of(fakeItem))
                .build();

        // Assert
        assertEquals(fakeItem, order.getItems().get(0));
    }

    @Test
    public void build_ShouldReturnAnOrder() {
        // Act
        Order order = orderBuilder.build();

        // Assert
        assertEquals(Order.class, order.getClass());
    }
}
