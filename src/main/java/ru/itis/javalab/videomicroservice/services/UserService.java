package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.models.User;

public interface UserService {
    User findUserById(Long id);
}
