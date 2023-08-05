package tech.rodolfoi.ecommercepublisher.dtos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.ItemInput;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ItemTests {
    private void assertInputEqualsItem(ItemInput itemInput, Item item) {
        assertEquals(itemInput.name(), item.getName());
        assertEquals(itemInput.image(), item.getImage());
        assertEquals(itemInput.qty(), item.getQty());
        assertEquals(itemInput.cost(), item.getCost(), 0.0);
        assertEquals(itemInput.currency(), item.getCurrency());
    }

    @Test
    public void fromItemInput_ShouldCreateAnItem() {
        // Arrange
        ItemInput fakeItemInput = new ItemInput("fake item", "fake image", 1, 10.0, "BRL");

        // Act
        Item item = Item.fromItemInput(fakeItemInput);

        // Assert
        assertInputEqualsItem(fakeItemInput, item);
    }

    @Test
    public void fromItemInput_ShouldCreateItemList() {
        // Arrange
        ItemInput fakeItemInput1 = new ItemInput("fake item 1", "fake image 1", 1, 10.0, "BRL");
        ItemInput fakeItemInput2 = new ItemInput("fake item 2", "fake image 2", 2, 20.0, "BRL");
        List<ItemInput> fakeItemInput = List.of(fakeItemInput1, fakeItemInput2);

        // Act
        List<Item> items = Item.fromItemInput(fakeItemInput);

        // Assert
        for (int i = 0; i < fakeItemInput.size(); i++)
            assertInputEqualsItem(fakeItemInput.get(i), items.get(i));
    }
}
