package tech.rodolfoi.ecommercepublisher.publishers;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommercepublisher.builders.OrderBuilder;
import tech.rodolfoi.ecommercepublisher.dtos.Order;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderPublisherTests {
    private static final String TOPIC_NAME = "orders";

    @Mock
    private PubSubTemplate pubSubTemplate;

    @InjectMocks
    private OrderPublisher orderPublisher;

    @Test
    public void publishMessage_ShouldPublishStringMessage() {
        // Arrange
        String fakeMessage = "fake message";

        // Act
        orderPublisher.publishMessage(fakeMessage);

        // Assert
        verify(pubSubTemplate, times(1)).publish(TOPIC_NAME, fakeMessage);
    }

    @Test
    public void publishMessage_ShouldPublishObjectMessage() {
        // Arrange
        Order fakeOrder = new OrderBuilder()
                .withOrder("fake order")
                .withOrigin("fake origin")
                .withItems(List.of())
                .build();
        Gson gson = new Gson();
        String json = gson.toJson(fakeOrder);

        // Act
        orderPublisher.publishMessage(fakeOrder);

        // Assert
        verify(pubSubTemplate, times(1)).publish(TOPIC_NAME, json);
    }
}