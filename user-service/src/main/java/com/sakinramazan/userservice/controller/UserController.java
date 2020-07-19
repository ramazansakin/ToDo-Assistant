package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final RestTemplate restTemplate;

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


    @GetMapping("/todos/{user_id}")
    public List<ToDoModel> invokePaymentService(@PathVariable Integer user_id) {
        final String uri = "http://todo-service/api/todos/all";

        ResponseEntity<List<ToDoModel>> result = restTemplate.exchange(uri, HttpMethod.GET, getHeader(),
                new ParameterizedTypeReference<List<ToDoModel>>() {
                }
        );
        return result.getBody();
    }

    private HttpEntity<String> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }
}