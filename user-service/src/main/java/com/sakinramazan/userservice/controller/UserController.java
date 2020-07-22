package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.dto.UserDTO;
import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.feign.client.UserClient;
import com.sakinramazan.userservice.feign.dto.UserResponse;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserClient client;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> all = userService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable @Range(min = 1, max = 100) Integer id) {
        UserDTO one = userService.getOne(id);
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> save(@RequestBody @Valid User user) {
        UserDTO actual = userService.addOne(user);
        return new ResponseEntity<>(actual, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid User user) {
        UserDTO actual = userService.updateOne(user);
        return new ResponseEntity<>(actual, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable @Range(min = 1, max = 200) Integer id) {
        Map<String, String> stringStringMap = userService.deleteOne(id);
        return new ResponseEntity<>(stringStringMap, HttpStatus.OK);
    }

    // RestTemplate api call usage sample
    @GetMapping("/all-todos")
    public ResponseEntity<List<ToDoModel>> getAllToDos() {
        List<ToDoModel> allToDosViaRestTemplate = userService.getAllToDosViaRestTemplate();
        return new ResponseEntity<>(allToDosViaRestTemplate, HttpStatus.OK);
    }

    // Feign Client api call usage samples
    @GetMapping("/feign-client/users")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse> users = client.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/feign-client/todo-service/{headline}")
    public ResponseEntity<Todo> getTodoByHeadline(@PathVariable String headline) {
        Todo todoByHeadline = userService.getTodoByHeadline(headline);
        return new ResponseEntity<>(todoByHeadline, HttpStatus.OK);
    }

    @GetMapping("/get-todos-by-user/{id}")
    public ResponseEntity<List<Todo>> getAllToDosByUser(@PathVariable @Range(min = 1, max = 200) Integer id) {
        List<Todo> allToDosByUser = userService.getAllToDosByUser(id);
        return new ResponseEntity<>(allToDosByUser, HttpStatus.OK);
    }

    @GetMapping("/all/address")
    public ResponseEntity<List<UserDTO>> getAllUsersWithAddress(@RequestParam @Range(min = 1, max = 200) Integer id) {
        List<UserDTO> usersByAddress = userService.getUsersByAddress(id);
        return new ResponseEntity<>(usersByAddress, HttpStatus.OK);
    }

    @GetMapping("/all/address/{city_name}")
    public ResponseEntity<List<UserDTO>> getAllUsersWithAddress(@PathVariable String city_name) {
        List<UserDTO> usersByAddressCityName = userService.getUsersByAddressCityName(city_name);
        return new ResponseEntity<>(usersByAddressCityName, HttpStatus.OK);
    }

}
