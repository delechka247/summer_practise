package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
    UserDto getUserFromJwt(String token);
    UserDto getUserById(Long id);
    UserDto getUserByResultId(Long resultId);
    UserDto findUserByTestId(Long testId);

}
