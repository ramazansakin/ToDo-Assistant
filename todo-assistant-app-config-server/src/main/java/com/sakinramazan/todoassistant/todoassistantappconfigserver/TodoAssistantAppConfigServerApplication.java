package com.sakinramazan.todoassistant.todoassistantappconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class TodoAssistantAppConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAssistantAppConfigServerApplication.class, args);
    }

}