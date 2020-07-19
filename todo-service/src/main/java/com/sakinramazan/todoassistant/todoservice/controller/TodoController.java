package com.sakinramazan.todoassistant.todoservice.controller;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import com.sakinramazan.todoassistant.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Todo getTodo(@PathVariable Integer id) {
        return todoService.getOne(id);
    }

    @PostMapping("/create")
    public void saveTodo(@RequestBody Todo todo) {
        todoService.addOne(todo);
    }

    @PutMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.updateOne(todo);
    }

    @DeleteMapping("/delete")
    public boolean deleteTodo(@RequestParam Integer id) {
        return todoService.deleteOne(id);
    }

    @GetMapping("/get-todo/{headline}")
    public Todo getByHeadline(@PathVariable String headline) {
        return todoService.getByHeadline(headline);
    }

}
