package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.AnswerDto;
import ru.itis.javalab.videomicroservice.models.Answer;
import ru.itis.javalab.videomicroservice.models.Question;
import ru.itis.javalab.videomicroservice.models.Result;
import ru.itis.javalab.videomicroservice.repositories.AnswersRepository;
import ru.itis.javalab.videomicroservice.repositories.QuestionsRepository;
import ru.itis.javalab.videomicroservice.repositories.ResultsRepository;

import java.util.List;

import static ru.itis.javalab.videomicroservice.dto.AnswerDto.from;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @Override
    public List<AnswerDto> getAnswersByQuestionId(Long questionId) {
        return AnswerDto.from(answersRepository.findAllByQuestion_Id(questionId));
    }

    @Override
    public List<AnswerDto> getAllAnswers() {
        return AnswerDto.from(answersRepository.findAll());
    }

    @Override
    public AnswerDto getAnswerById(Long answerId) {
        return from(answersRepository.findById(answerId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public AnswerDto addAnswer(AnswerDto answerDto, Long questionId) {
        Question question = questionsRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        Answer answer = Answer.builder()
                .content(answerDto.getContent())
                .accuracy(answerDto.getAccuracy())
                .question(question)
                .build();
        answersRepository.save(answer);
        return from(answer);
    }

    @Override
    public AnswerDto updateAnswer(AnswerDto answerDto, Long answerId) {
        Answer answer = answersRepository.findById(answerId).orElseThrow(IllegalArgumentException::new);
        answer.setContent(answerDto.getContent());
        answer.setAccuracy(answerDto.getAccuracy());
        answersRepository.save(answer);
        return from(answer);
    }

    @Override
    public AnswerDto deleteAnswer(Long answerId) {
        Answer answer = answersRepository.findById(answerId).orElseThrow(IllegalArgumentException::new);
        answersRepository.deleteById(answerId);
        return from(answer);
    }

    @Override
    public AnswerDto getAnswersByResultId(Long resultId) {
        Result result = resultsRepository.findById(resultId).orElseThrow(IllegalArgumentException::new);
        return from(answersRepository.findAnswerByResultsContains(result).orElseThrow(IllegalArgumentException::new));
    }

}
