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

    protected String subscription() {
        return createSubscription(this.topic, this.subscriptionName);
	}

    protected abstract void consume(BasicAcknowledgeablePubsubMessage message);

    public Consumer<BasicAcknowledgeablePubsubMessage> consumer() {
        return basicAcknowledgeablePubsubMessage -> consume(basicAcknowledgeablePubsubMessage);
    }

    public Subscriber consumeMessage() {
        return this.pubSubTemplate.subscribe(this.subscription(), this.consumer());
    }

	private String createSubscription(String topic, String subscription) {
        if (this.pubSubAdmin.getSubscription(subscription) != null)
            return subscription;

        Subscription createSubscription = this.pubSubAdmin.createSubscription(subscription, topic);
        return createSubscription.getName();
    }
}
