package com.sakinramazan.todoassistant.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableEurekaClient
@SpringBootApplication
@RestController
@RefreshScope
public class TodoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }


    @GetMapping("{sample.url}")
    public String getCenteralizeConfigSreverTest() {
        return "Hello from Git server";
    }

    @GetMapping("test")
    public String getTest() {
        return "Hello from Test api";
    }

}