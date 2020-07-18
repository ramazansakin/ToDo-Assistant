package com.sakinramazan.todoassistant.todoservice.service;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();

    Todo getOne(Integer id);

    void addOne(@RequestBody Todo todo);

    Todo updateOne(@RequestBody Todo todo);

    boolean deleteOne(Integer id);

    Todo getByHeadline(String headline);
}
