package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.dto.UserForm;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.repositories.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto add(UserForm userForm) {
        User newUser = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .age(userForm.getAge())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .state(User.State.ACTIVE)
                .role(User.Role.USER)
                .build();
        usersRepository.save(newUser);
        return UserDto.from(newUser);
    }
}