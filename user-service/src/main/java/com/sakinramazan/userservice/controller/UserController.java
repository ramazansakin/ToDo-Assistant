package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.feign.client.UserClient;
import com.sakinramazan.userservice.feign.dto.UserResponse;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public User get(@PathVariable Integer id) {
        return userService.getOne(id);
    }

    @PostMapping("/create")
    public User save(@RequestBody User user) {
        return userService.addOne(user);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.updateOne(user);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Integer id) {
        return userService.deleteOne(id);
    }


    // RestTemplate api call usage sample
    // TODO : edit and refactor api
    @GetMapping("/all-todos")
    public List<ToDoModel> invokePaymentService() {
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
    public List<Todo> getAllToDosByUser(@PathVariable Integer id) {
        return userService.getAllToDosByUser(id);
    }

}
