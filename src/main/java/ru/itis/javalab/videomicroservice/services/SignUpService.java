package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.dto.UserForm;

public interface SignUpService{
    UserDto add(UserForm userForm);
}
