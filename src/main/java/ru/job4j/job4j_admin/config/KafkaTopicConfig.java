package ru.job4j.job4j_admin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic saveRequestsTopic() {
        return TopicBuilder.name("save-requests-topic")
                .build();
    }

    @Bean
    public NewTopic updateRequestsTopic() {
        return TopicBuilder.name("update-requests-topic")
                .build();
    }

    @Bean
    public NewTopic deleteRequestsTopic() {
        return TopicBuilder.name("delete-requests-topic")
                .build();
    }

    @Bean
    public NewTopic findAllRequestsTopic() {
        return TopicBuilder.name("findAll-requests-topic")
                .build();
    }

    @Bean
    public NewTopic findByIdRequestsTopic() {
        return TopicBuilder.name("findById-requests-topic")
                .build();
    }

    @Bean
    public NewTopic findByNameRequestsTopic() {
        return TopicBuilder.name("findByName-requests-topic")
                .build();
    }
}
