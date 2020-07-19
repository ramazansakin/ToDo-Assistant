package com.sakinramazan.userservice.service.impl;

import com.sakinramazan.userservice.entity.User;
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
    public User addOne(User address) {
        return userRepository.save(address);
    }

    @Override
    public User updateOne(User address) {
        User one = getOne(address.getId());
        if (one != null)
            return userRepository.save(one);
        return null;
    }

    @Override
    public boolean deleteOne(Integer id) {
        return false;
    }
}
