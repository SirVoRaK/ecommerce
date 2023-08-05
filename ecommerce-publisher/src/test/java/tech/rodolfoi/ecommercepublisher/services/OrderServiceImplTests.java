package tech.rodolfoi.ecommercepublisher.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.dtos.Order;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.ItemInput;
import tech.rodolfoi.ecommercepublisher.dtos.inputs.OrderInput;
import tech.rodolfoi.ecommercepublisher.publishers.OrderPublisher;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTests {
    private OrderInput fakeOrderInput;

    @Mock
    private OrderPublisher orderPublisher;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        ItemInput fakeItemInput = new ItemInput("fake item", "fake image", 1, 10.0, "BRL");
        List<ItemInput> fakeItems = List.of(fakeItemInput);
        fakeOrderInput = new OrderInput("fake order", "fake origin", fakeItems);
    }

    @Test
    public void create_ShouldPublishMessage() {
        // Act
        orderService.create(fakeOrderInput);

        // Assert
        verify(orderPublisher, times(1)).publishMessage(any(Order.class));
    }
}
