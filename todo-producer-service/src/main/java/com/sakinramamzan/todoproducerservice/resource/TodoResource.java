package com.sakinramamzan.todoproducerservice.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakinramamzan.todoproducerservice.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
@Slf4j
public class TodoResource {

    private final KafkaTemplate<String, String> kafkaStringTemplate;

    private final KafkaTemplate<String, Todo> kafkaTodoTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    private static final String TOPIC_STRING = "Kafka_Sample_Topic";
    private static final String TOPIC_TODO = "Kafka_Todo_Topic";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable final String message) {
        log.info("String Message : " + message);
        kafkaStringTemplate.send(TOPIC_STRING, message);

        return "Message published successfully : " + message;
    }

    @PostMapping("/publish-todo")
    public String publishTodo(@RequestBody Todo todo) {
        log.info("Todo Message : " + todo);
        kafkaTodoTemplate.send(TOPIC_TODO, todo);

        return "Todo published successfully : " + todo;
    }

}
