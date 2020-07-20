package com.sakinramazan.userservice.feign.client;


import com.sakinramazan.userservice.feign.hystrix.ToDoServiceProxyFallback;
import com.sakinramazan.userservice.model.ToDoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "todo-service", url = "localhost:8095/api/todos", fallback = ToDoServiceProxyFallback.class)
public interface ToDoServiceProxy {

    @GetMapping("/get-todo/{headline}")
    ToDoModel getByHeadline(@PathVariable String headline);

}
