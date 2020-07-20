package com.sakinramazan.userservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.repository.UserRepository;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ToDoServiceProxy todoService;

    private final RestTemplate restTemplate;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public User addOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOne(User user) {
        User one = getOne(user.getId());
        if (one != null)
            return userRepository.save(one);

        return null;
    }

    @Override
    public boolean deleteOne(Integer id) {
        User one = getOne(id);
        if (one != null) {
            userRepository.save(one);
            return true;
        }
        return false;
    }

    @HystrixCommand(fallbackMethod = "getTodoByHeadline_Fallback")
    @Override
    public Todo getTodoByHeadline(String headline) {
        return todoService.getByHeadline(headline);
    }

    @Override
    public List<Todo> getAllToDosByUser(Integer id) {
        return todoService.getAllToDosByUser(id);
    }

    public Todo getTodoByHeadline_Fallback(String headline) {
        Todo defaultModel = new Todo();
        defaultModel.setHeadline("default Headline");
        defaultModel.setDetails("Default Details");
        return defaultModel;
    }

    public List<ToDoModel> getAllToDosViaRestTemplate() {
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
        return new HttpEntity<>("parameters", headers);
    }

}
