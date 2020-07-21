package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.feign.client.UserClient;
import com.sakinramazan.userservice.feign.dto.UserResponse;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserClient client;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable @Range(min = 1, max = 100) Integer id) {
        return userService.getOne(id);
    }

    @PostMapping("/create")
    public User save(@RequestBody @Valid User user) {
        return userService.addOne(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody @Valid User user) {
        return userService.updateOne(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable @Range(min = 1, max = 200) Integer id) {
        return userService.deleteOne(id);
    }

    // RestTemplate api call usage sample
    @GetMapping("/all-todos")
    public List<ToDoModel> getAllToDos() {
        return userService.getAllToDosViaRestTemplate();
    }

    // Feign Client api call usage samples
    @GetMapping("/feign-client/users")
    public List<UserResponse> getAllUser() {
        return client.getUsers();
    }

    @GetMapping("/feign-client/todo-service/{headline}")
    public Todo getTodoByHeadline(@PathVariable String headline) {
        return userService.getTodoByHeadline(headline);
    }

    @GetMapping("/get-todos-by-user/{id}")
    public List<Todo> getAllToDosByUser(@PathVariable @Range(min = 1, max = 50) Integer id) {
        return userService.getAllToDosByUser(id);
    }

}
