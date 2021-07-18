package ru.itis.javalab.videomicroservice.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.javalab.videomicroservice.dto.TestDto;

import java.util.List;

public interface TestsService {

    List<TestDto> getAllTests();

    TestDto getTestById(Long testId);

    TestDto addTest(TestDto testDto, UserDetails userDetails);

    TestDto updateTest(TestDto testDto, Long testId);

    TestDto deleteTest(Long testId);

    TestDto getTestByQuestionId(Long questionId);

}
