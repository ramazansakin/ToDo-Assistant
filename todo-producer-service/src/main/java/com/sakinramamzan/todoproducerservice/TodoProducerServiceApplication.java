package com.sakinramamzan.todoproducerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TodoProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoProducerServiceApplication.class, args);
    }

}
