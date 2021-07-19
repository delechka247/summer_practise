package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private Integer age;
    private String firstName;
    private String lastName;

    private List<TestDto> createdTests;

    private List<ResultDto> results;

    public static UserDto from(User user) {
        UserDto result = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        if(user.getCreatedTests() != null) {
            result.setCreatedTests(TestDto.from(user.getCreatedTests()));
        }
        if(user.getResults() != null) {
            result.setResults(ResultDto.from(user.getResults()));
        }
        return result;
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
