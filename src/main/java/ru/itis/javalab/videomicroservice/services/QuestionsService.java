package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.QuestionDto;

import java.util.List;

public interface QuestionsService {

    List<QuestionDto> getQuestionByTestId(Long testId);

    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long questionId);

    QuestionDto addQuestion(QuestionDto questionDto, Long testId);

    QuestionDto updateQuestion(QuestionDto questionDto, Long questionId);

    QuestionDto deleteQuestion(Long questionId);

    QuestionDto getQuestionByAnswerId(Long answerId);

}
