package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.ResultDto;
import ru.itis.javalab.videomicroservice.models.Answer;
import ru.itis.javalab.videomicroservice.models.Question;
import ru.itis.javalab.videomicroservice.models.Result;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.repositories.AnswersRepository;
import ru.itis.javalab.videomicroservice.repositories.QuestionsRepository;
import ru.itis.javalab.videomicroservice.repositories.ResultsRepository;
import ru.itis.javalab.videomicroservice.repositories.UsersRepository;
import ru.itis.javalab.videomicroservice.security.details.UserDetailsImpl;

import java.util.List;

import static ru.itis.javalab.videomicroservice.dto.ResultDto.from;

@Service
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public List<ResultDto> getResultsByAnswerId(Long answerId) {
        return from(resultsRepository.findAllByAnswer_Id(answerId));
    }

    @Override
    public List<ResultDto> getResultsByUserId(Long userId) {
        return from(resultsRepository.findAllByUser_Id(userId));
    }

    @Override
    public List<ResultDto> getAllResults() {
        return from(resultsRepository.findAll());
    }

    @Override
    public ResultDto getResultById(Long resultId) {
        return from(resultsRepository.findById(resultId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public ResultDto addResult(ResultDto resultDto, Long answerId, UserDetailsImpl userDetails) {
        Answer answer = answersRepository.findById(answerId).orElseThrow(IllegalArgumentException::new);
        User user = usersRepository.findByEmail(userDetails.getUsername()).orElseThrow(IllegalArgumentException::new);
        Result result = Result.builder()
                .correctness(resultDto.getCorrectness())
                .answer(answer)
                .user(user)
                .build();
        resultsRepository.save(result);
        return from(result);
    }

    @Override
    public ResultDto updateResult(ResultDto resultDto, Long resultId) {
        Result result = resultsRepository.findById(resultId).orElseThrow(IllegalArgumentException::new);
        result.setCorrectness(resultDto.getCorrectness());
        resultsRepository.save(result);
        return from(result);
    }

    @Override
    public ResultDto deleteResult(Long resultId) {
        Result result = resultsRepository.findById(resultId).orElseThrow(IllegalArgumentException::new);
        resultsRepository.deleteById(resultId);
        return from(result);
    }

    @Override
    public Integer getCountOfCorrectAnswersInTest(Long testId, Long userId) {
        List<Question> questions = questionsRepository.findAllByTest_Id(testId);
        List<Result> results = resultsRepository.findAllByUser_Id(userId);
        int count = 0;
        for (Question question : questions) {
            for (Result result : results) {
                List<Answer> answers = question.getAnswers();
                for (Answer answer : answers) {
                    if (answer.getId().equals(result.getAnswer().getId())) {
                        if (result.getCorrectness().toString().equals("CORRECT")) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}
