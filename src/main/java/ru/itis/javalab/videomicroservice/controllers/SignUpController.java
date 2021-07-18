package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.dto.UserForm;
import ru.itis.javalab.videomicroservice.services.SignUpServiceImpl;

@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpServiceImpl;

    @ApiOperation(value = "Регистрация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешная регистрация", response = UserDto.class)})
    @PostMapping("/signUp")
    public UserDto addUser(@RequestBody UserForm form) {
        return signUpServiceImpl.add(form);
    }

}
