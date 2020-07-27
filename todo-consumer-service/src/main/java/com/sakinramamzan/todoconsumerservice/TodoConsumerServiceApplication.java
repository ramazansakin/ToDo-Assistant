package com.sakinramamzan.todoconsumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TodoConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoConsumerServiceApplication.class, args);
    }

}
