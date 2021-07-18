package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.services.UsersService;


@RestController
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile")
    @ApiOperation(value = "Получение информации о пользователе")
    public UserDto getProfilePage(@RequestHeader("TOKEN") String token, @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        return usersService.getUserFromJwt(token);
    }


}
