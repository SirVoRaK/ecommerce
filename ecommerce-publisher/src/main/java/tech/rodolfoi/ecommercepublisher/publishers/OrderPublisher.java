package tech.rodolfoi.ecommercepublisher.publishers;

import org.springframework.stereotype.Service;

@Service
public class OrderPublisher extends BasePublisher {
    private static final String TOPIC_NAME = "orders";

    public OrderPublisher() {
        super(TOPIC_NAME);
    }
}
