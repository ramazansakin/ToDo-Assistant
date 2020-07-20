package com.sakinramazan.todoassistant.gatewayservice.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/userFallBack")
    public Mono<String> userServiceFallBack() {
        return Mono.just("User Service is taking too long to respond or down. Sorry, Please try again later");
    }

    @RequestMapping("/todoFallback")
    public Mono<String> todoServiceFallBack() {
        return Mono.just("Order Service is taking too long to respond or down. Sorry, Please try again later");
    }
}
