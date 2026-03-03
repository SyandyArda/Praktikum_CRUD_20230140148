package com.example.praktikum2.service.impl;

import com.example.praktikum2.mapper.UserMapper;
import com.example.praktikum2.model.dto.UserAddRequest;
import com.example.praktikum2.model.dto.UserDTO;
import com.example.praktikum2.model.entity.User;
import com.example.praktikum2.repository.UserRepository;
import com.example.praktikum2.service.UserService;
import com.example.praktikum2.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public UserDTO AddUser(UserAddRequest request) {
        validationUtil.validate(request);

        User saveUser = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .age(request.getAge())
                .build();

        userRepository.save(saveUser);

        UserDTO userDTO = UserMapper.MAPPER.toUserDtoData(saveUser);
        return userDTO;
    }
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTO = new ArrayList<>();
        for (User user : users) {
            userDTO.add(UserMapper.MAPPER.toUserDtoData(user));
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return UserMapper.MAPPER.toUserDtoData(user);
    }

    @Override
    public UserDTO UpdateUser(String id, UserAddRequest request) {
        validationUtil.validate(request);

        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        User user = User.builder()
                .id(existingUser.getId())
                .name(request.getName())
                .age(request.getAge())
                .build();

        userRepository.save(user);

        return UserMapper.MAPPER.toUserDtoData(user);
    }

    @Override
    public void DeleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        userRepository.delete(user);
    }
}