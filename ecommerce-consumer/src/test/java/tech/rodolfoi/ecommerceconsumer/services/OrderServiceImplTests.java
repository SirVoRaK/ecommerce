package tech.rodolfoi.ecommerceconsumer.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommerceconsumer.entities.Item;
import tech.rodolfoi.ecommerceconsumer.entities.Order;
import tech.rodolfoi.ecommerceconsumer.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTests {
    private Order fakeOrder;
    private final String fakeId = "1";
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        Item fakeItem = new Item();
        fakeItem.setName("fake item");
        fakeItem.setCost(10.0);
        fakeItem.setQty(2);

        Item[] fakeItems = new Item[1];
        fakeItems[0] = fakeItem;

        fakeOrder = new Order();
        fakeOrder.setId(fakeId);
        fakeOrder.setOrder("fake order");
        fakeOrder.setItems(fakeItems);
    }

    @Test
    public void findAll_ShouldReturnEmptyList() {
        // Arrange
        given(orderRepository.findAll()).willReturn(new ArrayList<>());

        // Act
        List<Order> orders = orderService.findAll();

        // Assert
        assert(orders).isEmpty();
    }

    @Test
    public void findById_ShouldReturnAnOrder() {
        // Arrange
        given(orderRepository.findById(fakeId)).willReturn(Optional.of(fakeOrder));

        // Act
        Order order = orderService.findById(fakeId);

        // Assert
        assert(order).equals(fakeOrder);
    }

    @Test
    public void findById_ShouldReturnNull() {
        // Arrange
        given(orderRepository.findById(fakeId)).willReturn(Optional.empty());

        // Act
        Order order = orderService.findById(fakeId);

        // Assert
        assert (order) == null;
    }

    @Test
    public void save_ShouldCallSaveMethod() {
        // Arrange

        // Act
        orderService.save(fakeOrder);

        // Assert
        verify(orderRepository, times(1)).save(fakeOrder);
    }
}
