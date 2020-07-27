package com.sakinramamzan.todoconsumerservice.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakinramamzan.todoconsumerservice.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TodoConsumer {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @KafkaListener(topics = "Kafka_Sample_Topic", containerFactory = "stringKafkaListenerFactory", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message : " + message);
    }

    @KafkaListener(topics = "Kafka_Todo_Topic", containerFactory = "todoKafkaListenerFactory", groupId = "group_json")
    public void consumeJson(Todo todo) {
        System.out.println("Consumed Todo : " + todo);
    }

}
