package com.sakinramazan.userservice.feign.client;


import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.feign.hystrix.ToDoServiceProxyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "todo-service", url = "localhost:9090/todo-service/api/todos", fallback = ToDoServiceProxyFallback.class)
public interface ToDoServiceProxy {

    @GetMapping("/get-todo/{headline}")
    Todo getByHeadline(@PathVariable String headline);

    @GetMapping("/get-todos-by-user/{id}")
    List<Todo> getAllToDosByUser(@PathVariable Integer id);

}
