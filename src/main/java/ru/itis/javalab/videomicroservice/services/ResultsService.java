package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.ResultDto;
import ru.itis.javalab.videomicroservice.security.details.UserDetailsImpl;

import java.util.List;

public interface ResultsService {

    List<ResultDto> getResultsByAnswerId(Long answerId);

    List<ResultDto> getResultsByUserId(Long userId);

    List<ResultDto> getAllResults();

    ResultDto getResultById(Long resultId);

    ResultDto addResult(ResultDto resultDto, Long answerId, UserDetailsImpl userDetails);

    ResultDto updateResult(ResultDto resultDto, Long resultId);

    ResultDto deleteResult(Long resultId);

    Integer getCountOfCorrectAnswersInTest(Long testId, Long userId);
}
