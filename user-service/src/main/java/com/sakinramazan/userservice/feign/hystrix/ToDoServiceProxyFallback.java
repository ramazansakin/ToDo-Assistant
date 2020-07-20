package com.sakinramazan.userservice.feign.hystrix;

import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import org.springframework.stereotype.Component;

@Component
public class ToDoServiceProxyFallback implements ToDoServiceProxy {
    @Override
    public Todo getByHeadline(String headline) {
        Todo defaultModel = new Todo();
        defaultModel.setHeadline("Default Headline");
        defaultModel.setDetails("Default Details");
        return defaultModel;
    }
}