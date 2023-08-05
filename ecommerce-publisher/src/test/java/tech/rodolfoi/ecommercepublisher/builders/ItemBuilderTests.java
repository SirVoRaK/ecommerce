package tech.rodolfoi.ecommercepublisher.builders;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.dtos.Item;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemBuilderTests {
    private ItemBuilder itemBuilder;

    @Before
    public void setUp() {
        itemBuilder = new ItemBuilder();
    }

    @Test
    public void withName_ShouldSetName() {
        // Arrange
        String fakeName = "fake name";

        // Act
        Item item = itemBuilder
                .withName(fakeName)
                .build();

        // Assert
        assertEquals(fakeName, item.getName());
    }

    @Test
    public void withQty_ShouldSetQty() {
        // Arrange
        int fakeQty = 1;

        // Act
        Item item = itemBuilder
                .withQty(fakeQty)
                .build();

        // Assert
        assertEquals(fakeQty, item.getQty());
    }

    @Test
    public void withCost_ShouldSetCost() {
        // Arrange
        Double fakeCost = 1.0;

        // Act
        Item item = itemBuilder
                .withCost(fakeCost)
                .build();

        // Assert
        assertEquals(fakeCost, item.getCost());
    }

    @Test
    public void withImage_ShouldSetImage() {
        // Arrange
        String fakeImage = "fake image";

        // Act
        Item item = itemBuilder
                .withImage(fakeImage)
                .build();

        // Assert
        assertEquals(fakeImage, item.getImage());
    }

    @Test
    public void withCurrency_ShouldSetCurrency() {
        // Arrange
        String fakeCurrency = "fake currency";

        // Act
        Item item = itemBuilder
                .withCurrency(fakeCurrency)
                .build();

        // Assert
        assertEquals(fakeCurrency, item.getCurrency());
    }
}
