package com.sakinramazan.todoassistant.todoservice.controller;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import com.sakinramazan.todoassistant.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping(value = "/all")
    public List<Todo> getAllTodos() {
        return todoService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Todo getTodo(@PathVariable Integer id) {
        return todoService.getOne(id);
    }

    @PostMapping(value = "/create")
    public void saveTodo(@RequestBody Todo todo) {
        todoService.addOne(todo);
    }

    @PutMapping(value = "/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.updateOne(todo);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteTodo(@RequestParam Integer id) {
        return todoService.deleteOne(id);
    }

}
