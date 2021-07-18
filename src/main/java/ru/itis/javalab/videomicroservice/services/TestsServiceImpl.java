package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.TestDto;
import ru.itis.javalab.videomicroservice.models.Question;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.repositories.QuestionsRepository;
import ru.itis.javalab.videomicroservice.repositories.TestsRepository;
import ru.itis.javalab.videomicroservice.repositories.UsersRepository;

import java.util.List;

import static ru.itis.javalab.videomicroservice.dto.TestDto.from;

@Service
public class TestsServiceImpl implements TestsService {

    @Autowired
    private TestsRepository testsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public List<TestDto> getAllTests() {
        return from(testsRepository.findAll());
    }

    @Override
    public TestDto getTestById(Long testId) {
        return from(testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public TestDto addTest(TestDto testDto, UserDetails userDetails) {
        User user = usersRepository.findByEmail(userDetails.getUsername()).orElseThrow(IllegalArgumentException::new);
        Test test = Test.builder()
                .name(testDto.getName())
                .title(testDto.getTitle())
                .creator(user)
                .build();
        testsRepository.save(test);
        return from(test);
    }

    @Override
    public TestDto updateTest(TestDto testDto, Long testId) {
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);
        test.setName(testDto.getName());
        test.setTitle(testDto.getTitle());
        testsRepository.save(test);
        return from(test);
    }

    @Override
    public TestDto deleteTest(Long testId) {
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);
        testsRepository.deleteById(testId);
        return from(test);
    }

    @Override
    public TestDto getTestByQuestionId(Long questionId) {
        Question question = questionsRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        return  from(testsRepository.findTestByQuestionsContains(question).orElseThrow(IllegalArgumentException::new));
    }

}
