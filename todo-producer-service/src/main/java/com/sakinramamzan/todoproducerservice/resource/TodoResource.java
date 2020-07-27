package com.sakinramamzan.todoproducerservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakinramamzan.todoproducerservice.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
@Slf4j
public class TodoResource {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private static final String TOPIC = "Kafka_Sample_Topic";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable final String message) throws JsonProcessingException {
        Todo todo = new Todo(1, "Sample Headline", "Sample Details", 1);
        String todoJsonStr = objectMapper.writeValueAsString(todo);
        log.info("Message : " + todoJsonStr);
        kafkaTemplate.send(TOPIC, todoJsonStr);

        return "Message published successfully : " + todoJsonStr;
    }

}
