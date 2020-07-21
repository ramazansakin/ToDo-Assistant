package com.sakinramazan.todoassistant.todoservice.controller;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import com.sakinramazan.todoassistant.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@Slf4j
@CrossOrigin("*")
public class TodoController {

    @Value("${server.port}")
    private int port;

    private final TodoService todoService;

    @GetMapping("/all")
    public List<Todo> getAllTodos() {
        log.info("All ToDo response via server port : " + port);
        return todoService.getAll();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable @Range(min = 1, max = 200) Integer id) {
        return todoService.getOne(id);
    }

    @PostMapping("/create")
    public Todo saveTodo(@RequestBody @Valid Todo todo) {
        return todoService.addOne(todo);
    }

    @PutMapping("/update")
    public Todo updateTodo(@RequestBody @Valid Todo todo) {
        return todoService.updateOne(todo);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTodo(@PathVariable @Range(min = 1, max = 200) Integer id) {
        return todoService.deleteOne(id);
    }

    @GetMapping("/get-todo/{headline}")
    public Todo getByHeadline(@PathVariable String headline) {
        return todoService.getByHeadline(headline);
    }

    @GetMapping("/get-todos-by-user/{id}")
    public List<Todo> getAllToDosByUser(@PathVariable @Range(min = 1, max = 200) Integer id) {
        return todoService.getAllToDosByUser(id);
    }
}
