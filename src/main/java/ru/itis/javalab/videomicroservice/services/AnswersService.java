package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.AnswerDto;

import java.util.List;

public interface AnswersService {

    List<AnswerDto> getAnswersByQuestionId(Long questionId);

    List<AnswerDto> getAllAnswers();

    AnswerDto getAnswerById(Long answerId);

    AnswerDto addAnswer(AnswerDto answerDto, Long questionId);

    AnswerDto updateAnswer(AnswerDto answerDto, Long answerId);

    AnswerDto deleteAnswer(Long answerId);

    AnswerDto getAnswersByResultId(Long resultId);
}
