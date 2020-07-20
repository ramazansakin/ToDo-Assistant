package com.sakinramazan.userservice.service;

import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.model.ToDoModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOne(Integer id);

    User addOne(@RequestBody User address);

    User updateOne(@RequestBody User address);

    boolean deleteOne(Integer id);

    ToDoModel getTodoByHeadline(String headline);
}
