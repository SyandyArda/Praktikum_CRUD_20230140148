package com.example.praktikum2.mapper;

import com.example.praktikum2.model.dto.UserDTO;
import com.example.praktikum2.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserDTO toUserDtoData (User user);
}