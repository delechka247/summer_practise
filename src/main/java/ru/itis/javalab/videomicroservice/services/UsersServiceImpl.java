package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.models.Result;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.repositories.ResultsRepository;
import ru.itis.javalab.videomicroservice.repositories.TestsRepository;
import ru.itis.javalab.videomicroservice.repositories.UsersRepository;
import ru.itis.javalab.videomicroservice.security.jwt.utils.JwtDecoder;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private TestsRepository testsRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto getUserFromJwt(String token) {
        User user = jwtDecoder.getUserFromJwt(token);

        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(usersRepository.findUserById(id).get());
    }

    @Override
    public UserDto findUserByTestId(Long testId) {      //my
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);
        return UserDto.from(usersRepository.findUserByCreatedTestsContains(test).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public UserDto getUserByResultId(Long resultId) {
        Result result = resultsRepository.findById(resultId).orElseThrow(IllegalArgumentException::new);
        return UserDto.from(usersRepository.findUserByResultsContains(result).orElseThrow(IllegalArgumentException::new));
    }

}
