package com.sakinramazan.userservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.repository.UserRepository;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ToDoServiceProxy todoService;

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
        if (user != null && user.getId() != null) {
            User one = getOne(user.getId());
            if (one != null)
                return userRepository.save(one);
        }
        return null;
    }

    @Override
    public boolean deleteOne(Integer id) {
        if (id != null) {
            User one = getOne(id);
            if (one != null) {
                userRepository.save(one);
                return true;
            }
        }
        return false;
    }

    @HystrixCommand(fallbackMethod = "getTodoByHeadline_Fallback")
    @Override
    public ToDoModel getTodoByHeadline(String headline) {
        return todoService.getByHeadline(headline);
    }

    public ToDoModel getTodoByHeadline_Fallback(String headline) {
        ToDoModel defaultModel = new ToDoModel();
        defaultModel.setHeadline("default Headline");
        defaultModel.setDetails("Default Details");

        return defaultModel;
    }
}
