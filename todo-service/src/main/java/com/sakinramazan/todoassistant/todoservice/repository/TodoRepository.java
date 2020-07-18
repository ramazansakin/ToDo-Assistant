package com.sakinramazan.todoassistant.todoservice.repository;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Todo getByHeadline(String headline);
}
