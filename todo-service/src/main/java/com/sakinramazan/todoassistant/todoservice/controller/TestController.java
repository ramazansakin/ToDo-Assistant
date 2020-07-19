package com.sakinramazan.todoassistant.todoservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RefreshScope
public class TestController {

    @GetMapping("{sample.url}")
    public String getCenteralizeConfigSreverTest() {
        return "Hello from Git server";
    }

    @GetMapping("test")
    public String getTest() {
        return "Hello from Test api";
    }
}
