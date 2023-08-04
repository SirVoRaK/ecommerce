package tech.rodolfoi.ecommerceconsumer.consumers;

import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Subscription;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tech.rodolfoi.ecommerceconsumer.entities.Order;
import tech.rodolfoi.ecommerceconsumer.services.OrderServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersConsumerTests {
    private static final String topic = "orders";
    private static final String subscriptionName = "orders-subscription";
    private Subscription fakeSubscription;
    private Order fakeOrder;
    private PubsubMessage fakeMessage;
    @Mock
    private OrderServiceImpl orderService;
    @Mock
    private PubSubTemplate pubSubTemplate;
    @Mock
    private PubSubAdmin pubSubAdmin;

    @InjectMocks
    private OrdersConsumer ordersConsumer;

    @Before
    public void setUp() {
        fakeSubscription = Subscription.newBuilder().setName(subscriptionName).build();
        fakeOrder = new Order();

        Gson gson = new Gson();
        String fakeOrderJson = gson.toJson(fakeOrder);
        fakeMessage = PubsubMessage.newBuilder()
                .setData(ByteString.copyFrom(fakeOrderJson.getBytes()))
                .build();
    }

    @Test
    public void subscribe_ShouldCreateSubscriptionAndSubscribe() {
        // Arrange
        given(pubSubAdmin.getSubscription(subscriptionName)).willReturn(null);
        given(pubSubAdmin.createSubscription(subscriptionName, topic)).willReturn(fakeSubscription);

        // Act
        ordersConsumer.subscribe();

        // Assert
        verify(pubSubTemplate, times(1)).subscribe(eq(subscriptionName), ArgumentMatchers.any());
        verify(pubSubAdmin, times(1)).createSubscription(subscriptionName, topic);
    }

    @Test
    public void subscribe_ShouldNotCreateSubscriptionAndSubscribe() {
        // Arrange
        given(pubSubAdmin.getSubscription(subscriptionName)).willReturn(fakeSubscription);

        // Act
        ordersConsumer.subscribe();

        // Assert
        verify(pubSubTemplate, times(1)).subscribe(eq(subscriptionName), ArgumentMatchers.any());
    }

    @Test
    public void consume_ShouldSaveAnOrderJsonAndAcceptMessage() {
        // Arrange
        BasicAcknowledgeablePubsubMessage fakeAcknowledgeableMessage = mock(BasicAcknowledgeablePubsubMessage.class);
        given(fakeAcknowledgeableMessage.getPubsubMessage()).willReturn(fakeMessage);

        // Act
        ordersConsumer.consume(fakeAcknowledgeableMessage);

        // Assert
        verify(orderService, times(1)).save(any(Order.class));
        verify(fakeAcknowledgeableMessage, times(1)).ack();
        verify(fakeAcknowledgeableMessage, times(0)).nack();
    }

    @Test
    public void consume_ShouldRejectMessageWhenErrorOnSave() {
        // Arrange
        BasicAcknowledgeablePubsubMessage fakeAcknowledgeableMessage = mock(BasicAcknowledgeablePubsubMessage.class);
        given(fakeAcknowledgeableMessage.getPubsubMessage()).willReturn(fakeMessage);
        doThrow(new RuntimeException()).when(orderService).save(any(Order.class));

        // Act
        ordersConsumer.consume(fakeAcknowledgeableMessage);

        // Assert
        verify(orderService, times(1)).save(any(Order.class));
        verify(fakeAcknowledgeableMessage, times(1)).nack();
        verify(fakeAcknowledgeableMessage, times(0)).ack();
    }
}
