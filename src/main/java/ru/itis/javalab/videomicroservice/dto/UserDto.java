package ru.itis.javalab.videomicroservice.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.javalab.videomicroservice.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private String userName;
    private String email;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}