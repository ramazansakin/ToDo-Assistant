package com.sakinramazan.todoassistant.todoservice.service.impl;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import com.sakinramazan.todoassistant.todoservice.repository.TodoRepository;
import com.sakinramazan.todoassistant.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getOne(Integer id) {
        Optional<Todo> byId = todoRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public void addOne(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public Todo updateOne(Todo todo) {
        if (todo != null && todo.getId() != null) {
            Todo one = getOne(todo.getId());
            if (one != null)
                return todoRepository.save(todo);
        }
        return null;
    }

    @Override
    public boolean deleteOne(Integer id) {
        if (id != null) {
            Todo one = getOne(id);
            if (one != null) {
                todoRepository.delete(one);
                return true;
            }
        }
        return false;
    }

    @Override
    public Todo getByHeadline(String headline) {
        return todoRepository.getByHeadline(headline);
    }

    @Override
    public List<Todo> getAllToDosByUser(Integer id) {
        return todoRepository.getAllByUserId(id);
    }
}
