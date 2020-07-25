package com.sakinramazan.todoassistant.gatewayservice.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.sakinramazan.todoassistant.gatewayservice.model.GatewayClientResponse;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
class GatewayFallbackProvider implements FallbackProvider {

    private static final String DEFAULT_MESSAGE = "Service is not available.";

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
        } else {
            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
        }
    }

}