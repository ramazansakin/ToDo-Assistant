package com.sakinramazan.todoassistant.todoassistantappconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TodoAssistantAppConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAssistantAppConfigServerApplication.class, args);
	}

}
