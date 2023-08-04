package tech.rodolfoi.ecommerceconsumer.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.gson.Gson;
import com.google.pubsub.v1.PubsubMessage;

import tech.rodolfoi.ecommerceconsumer.entities.Order;
import tech.rodolfoi.ecommerceconsumer.services.interfaces.OrderService;

@Component
public class OrdersConsumer extends BaseConsumer {
    @Autowired
    private OrderService orderService;

    private static final String topic = "orders";
    private static final String subscriptionName = "orders-subscription";

    public OrdersConsumer() {
		super(topic, subscriptionName);
	}

	@Autowired
    private PubSubTemplate pubSubTemplate;

    @Override
    public void consume(BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {
        PubsubMessage message = basicAcknowledgeablePubsubMessage.getPubsubMessage();

        Boolean savedSuccessfully = this.saveOrder(message);

        if (savedSuccessfully)
            basicAcknowledgeablePubsubMessage.ack();
        else
            basicAcknowledgeablePubsubMessage.nack();
    }

    private Boolean saveOrder(PubsubMessage message) {
        try {
            String orderJson = message.getData().toStringUtf8();

            Gson gson = new Gson();
            Order order = gson.fromJson(orderJson, Order.class);

            orderService.save(order);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
        return true;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void subscribe() {
        super.consumeMessage();
    }
}
