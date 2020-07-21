package com.sakinramazan.userservice.service;

import com.sakinramazan.userservice.entity.Todo;
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

    Todo getTodoByHeadline(String headline);

    List<Todo> getAllToDosByUser(Integer id);

    List<ToDoModel> getAllToDosViaRestTemplate();

    List<User> getUsersByAddress(Integer address_id);

    List<User> getUsersByAddressCityName(String city);

}
