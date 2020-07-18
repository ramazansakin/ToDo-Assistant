package com.sakinramazan.todoassistant.todoservice.repository;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Todo getByHeadline(String headline);
}
