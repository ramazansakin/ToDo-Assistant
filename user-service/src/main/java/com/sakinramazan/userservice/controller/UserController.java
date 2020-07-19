package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

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


}
