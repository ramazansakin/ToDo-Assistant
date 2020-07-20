package com.sakinramazan.userservice.feign.hystrix;

import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoServiceProxyFallback implements ToDoServiceProxy {
    @Override
    public Todo getByHeadline(String headline) {
        Todo defaultModel = new Todo();
        defaultModel.setHeadline("Default Headline");
        defaultModel.setDetails("Default Details");
        return defaultModel;
    }

    @Override
    public List<Todo> getAllToDosByUser(Integer id) {
        // return empty list
        // we can add any details via converting response object
        // have fun
        return new ArrayList<>();
    }
}