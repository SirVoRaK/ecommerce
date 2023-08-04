package tech.rodolfoi.ecommerceconsumer.consumers;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.Subscription;

public abstract class BaseConsumer {
    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private PubSubAdmin pubSubAdmin;

    protected String topic;
    protected String subscriptionName;

    protected BaseConsumer(String topic, String subscriptionName) {
        this.topic = topic;
        this.subscriptionName = subscriptionName;
    }

    protected abstract void consume(BasicAcknowledgeablePubsubMessage message);
    protected abstract void subscribe();

    public Consumer<BasicAcknowledgeablePubsubMessage> consumer() {
        return this::consume;
    }

    protected void consumeMessage() {
        pubSubTemplate.subscribe(subscription(), consumer());
    }

    protected String subscription() {
        if (pubSubAdmin.getSubscription(subscriptionName) != null)
            return subscriptionName;

        Subscription createSubscription = pubSubAdmin.createSubscription(subscriptionName, topic);
        return createSubscription.getName();
    }
}
