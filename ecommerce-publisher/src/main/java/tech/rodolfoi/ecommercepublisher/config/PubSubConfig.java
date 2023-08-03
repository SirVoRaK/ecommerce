package tech.rodolfoi.ecommercepublisher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.spring.pubsub.PubSubAdmin;

@Configuration
public class PubSubConfig {
    @Autowired
    private PubSubAdmin pubSubAdmin;

    @Bean
    public void createTopicIfNotExists() {
        String topicName = "orders";
        if (pubSubAdmin.getTopic(topicName) == null)
            pubSubAdmin.createTopic(topicName);
    }
}
