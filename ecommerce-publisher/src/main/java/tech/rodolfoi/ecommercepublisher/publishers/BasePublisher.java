package tech.rodolfoi.ecommercepublisher.publishers;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.gson.Gson;

public abstract class BasePublisher {
    protected String topicName;

    @Autowired
    protected PubSubTemplate pubSubTemplate;

    protected BasePublisher(String topicName) {
        this.topicName = topicName;
    }

    public void publishMessage(String message) {
        pubSubTemplate.publish(topicName, message);
    }

    public <T> void publishMessage(T message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        this.publishMessage(json);
    }
}
