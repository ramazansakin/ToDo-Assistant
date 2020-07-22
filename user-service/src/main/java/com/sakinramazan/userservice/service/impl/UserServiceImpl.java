package com.sakinramazan.userservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sakinramazan.userservice.dto.UserDTO;
import com.sakinramazan.userservice.entity.Address;
import com.sakinramazan.userservice.entity.Todo;
import com.sakinramazan.userservice.entity.User;
import com.sakinramazan.userservice.exception.UserNotFoundException;
import com.sakinramazan.userservice.feign.client.ToDoServiceProxy;
import com.sakinramazan.userservice.model.ToDoModel;
import com.sakinramazan.userservice.repository.UserRepository;
import com.sakinramazan.userservice.service.AddressService;
import com.sakinramazan.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressService addressService;

    private final ToDoServiceProxy todoService;

    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Cacheable(value = "users")
    @Override
    public List<UserDTO> getAll() {
        List<User> all = userRepository.findAll();
        return all.stream().
                map(user -> modelMapper.map(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO getOne(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(user, UserDTO.class);
    }

    //  @CachePut(value = "users", key = "#result.id", unless = "#result == null")
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public UserDTO addOne(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        User save = userRepository.save(user);
        return modelMapper.map(save, UserDTO.class);
    }

    //  @CachePut(value = "users", key = "#result.id", unless = "#result == null")
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public UserDTO updateOne(User user) {
        // we need to check dto id or put another validation
        // to prevent db null field exceptions
        // we can customize the exception to handle on
        // genericExpHandler specifically
        if (user.getId() == null)
            throw new RuntimeException("Id must not be null for update entity");
        // check whether there is a such user or not
        getOne(user.getId());
        User save = userRepository.save(user);
        return modelMapper.map(save, UserDTO.class);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Map<String, String> deleteOne(Integer id) {
        // check whether there is a such user or not
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        Map<String, String> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE.toString());
        return response;
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

    // If there is any problem, return a default entity
    // or do something u want
    private Todo getTodoByHeadline_Fallback(String headline) {
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

    @Override
    public List<UserDTO> getUsersByAddress(Integer address_id) {
        Address one = addressService.getOne(address_id);
        List<User> allByAddress = userRepository.getAllByAddress(one);
        return allByAddress.stream().
                map(user -> modelMapper.map(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByAddressCityName(String city) {
        List<User> allByAddress_city = userRepository.getAllByAddress_City(city);
        return allByAddress_city.stream().
                map(user -> modelMapper.map(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    private HttpEntity<String> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>("parameters", headers);
    }

}
