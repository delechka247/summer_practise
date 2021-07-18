package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.QuestionDto;
import ru.itis.javalab.videomicroservice.models.Answer;
import ru.itis.javalab.videomicroservice.models.Question;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.repositories.AnswersRepository;
import ru.itis.javalab.videomicroservice.repositories.QuestionsRepository;
import ru.itis.javalab.videomicroservice.repositories.TestsRepository;

import java.util.List;

import static  ru.itis.javalab.videomicroservice.dto.QuestionDto.from;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private TestsRepository testsRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Override
    public List<QuestionDto> getQuestionByTestId(Long testId) {
        return from(questionsRepository.findAllByTest_Id(testId));
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return from(questionsRepository.findAll());
    }

    @Override
    public QuestionDto getQuestionById(Long questionId) {
        return from(questionsRepository.findById(questionId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto, Long testId) {
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);
        Question question = Question.builder()
                .content(questionDto.getContent())
                .test(test)
                .build();
        questionsRepository.save(question);
        return  from(question);
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto questionDto, Long questionId) {
        Question question = questionsRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        question.setContent(questionDto.getContent());
        questionsRepository.save(question);
        return from(question);
    }

    @Override
    public QuestionDto deleteQuestion(Long questionId) {
        Question question = questionsRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        questionsRepository.deleteById(questionId);
        return from(question);
    }

    @Override
    public QuestionDto getQuestionByAnswerId(Long answerId) {
        Answer answer = answersRepository.findById(answerId).orElseThrow(IllegalArgumentException::new);
        return from(questionsRepository.findQuestionByAnswersContains(answer).orElseThrow(IllegalArgumentException::new));
    }

}
