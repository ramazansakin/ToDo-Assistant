package com.sakinramamzan.todoproducerservice.resource;

import com.sakinramamzan.todoproducerservice.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
public class TodoResource {

    @Autowired
    private KafkaTemplate<String, Todo> kafkaTemplate;

    private static final String TOPIC = "Kafka_Topic_Name_Here";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable final String message) {

        kafkaTemplate.send(TOPIC, new Todo(1, "Sample Headline", "Sample Details", 1));

        return "Message published successfully!";
    }

}
