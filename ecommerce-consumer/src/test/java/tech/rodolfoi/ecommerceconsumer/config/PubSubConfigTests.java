package tech.rodolfoi.ecommerceconsumer.config;

import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.pubsub.v1.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PubSubConfigTests {
    private static final String topicName = "orders";
    private Topic fakeTopic;

    @Mock
    private PubSubAdmin pubSubAdmin;

    @InjectMocks
    private PubSubConfig pubSubConfig;

    @Before
    public void setUp() {
        fakeTopic = Topic.newBuilder().setName(topicName).build();
    }

    @Test
    public void createTopicIfNotExists_ShouldCreateTopic() {
        // Arrange
        given(pubSubAdmin.getTopic(topicName)).willReturn(null);

        // Act
        pubSubConfig.createTopicIfNotExists();

        // Assert
        verify(pubSubAdmin, times(1)).createTopic(topicName);
    }

    @Test
    public void createTopicIfNotExists_ShouldNotCreateTopic() {
        // Arrange
        given(pubSubAdmin.getTopic(topicName)).willReturn(fakeTopic);

        // Act
        pubSubConfig.createTopicIfNotExists();

        // Assert
        verify(pubSubAdmin, times(0)).createTopic(topicName);
    }

    @Test
    public void createTopicIfNotExists_ShouldNotCreateTopicAndCatchException() {
        // Arrange
        given(pubSubAdmin.getTopic(topicName)).willThrow(new RuntimeException("Error"));

        // Act
        pubSubConfig.createTopicIfNotExists();

        // Assert
        verify(pubSubAdmin, times(0)).createTopic(topicName);
        verify(pubSubAdmin, times(1)).getTopic(topicName);
    }

    @Test
    public void createTopicIfNotExists_ShouldCreateTopicAndCatchException() {
        // Arrange
        given(pubSubAdmin.getTopic(topicName)).willReturn(null);
        given(pubSubAdmin.createTopic(topicName)).willThrow(new RuntimeException("Error"));

        // Act
        pubSubConfig.createTopicIfNotExists();

        // Assert
        verify(pubSubAdmin, times(1)).createTopic(topicName);
    }
}
