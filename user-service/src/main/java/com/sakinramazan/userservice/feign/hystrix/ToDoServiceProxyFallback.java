package com.sakinramazan.userservice.feign.hystrix;

import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import com.sakinramazan.userservice.model.ToDoModel;
import org.springframework.stereotype.Component;

@Component
public class ToDoServiceProxyFallback implements ToDoServiceProxy {
    @Override
    public ToDoModel getByHeadline(String headline) {
        ToDoModel defaultModel = new ToDoModel();
        defaultModel.setHeadline("Default Headline");
        defaultModel.setDetails("Default Details");
        return null;
    }
}