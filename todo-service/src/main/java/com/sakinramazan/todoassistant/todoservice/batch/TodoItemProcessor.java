package com.sakinramazan.todoassistant.todoservice.batch;

import com.sakinramazan.todoassistant.todoservice.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class TodoItemProcessor implements ItemProcessor<Todo, Todo> {

    @Override
    public Todo process(final Todo todo) {
        String details = todo.getDetails();
        String headline = todo.getHeadline();

        Todo updatedTodo = new Todo();
        // You can add any prefix or postfix here
        // OR
        // make any changes on
        updatedTodo.setId(todo.getId());
        updatedTodo.setUserId(todo.getUserId());
        updatedTodo.setHeadline(headline + " - Headline post-fix via Batch-service");
        updatedTodo.setDetails("Details prefix via Batch-service : " + details);

        log.info("-- Converting Todo : (" + todo + ") into -> (" + updatedTodo + ") --");
        return updatedTodo;
    }

}
