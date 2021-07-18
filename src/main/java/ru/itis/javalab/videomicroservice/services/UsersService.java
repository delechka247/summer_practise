package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
    UserDto getUserFromJwt(String token);
}
