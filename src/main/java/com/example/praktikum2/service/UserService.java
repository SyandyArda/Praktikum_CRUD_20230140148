package com.example.praktikum2.service;

import com.example.praktikum2.model.dto.UserAddRequest;
import com.example.praktikum2.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO AddUser(UserAddRequest request);
    List<UserDTO> getAllUser();
    UserDTO getUserById(String id);
    UserDTO UpdateUser(String id, UserAddRequest request);
    void DeleteUser(String id);
}
