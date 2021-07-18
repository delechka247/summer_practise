package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.AnswerDto;
import ru.itis.javalab.videomicroservice.dto.QuestionDto;
import ru.itis.javalab.videomicroservice.dto.ResultDto;
import ru.itis.javalab.videomicroservice.services.AnswersService;
import ru.itis.javalab.videomicroservice.services.QuestionsService;
import ru.itis.javalab.videomicroservice.services.ResultsService;

import java.util.List;

@RestController
public class AnswersController {

    @Autowired
    private AnswersService answersService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private ResultsService resultsService;

    @ApiOperation(value = "Получение всех ответов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answers")
    public ResponseEntity<List<AnswerDto>> getAnswers() {
        return ResponseEntity.ok(answersService.getAllAnswers());
    }

    @ApiOperation(value = "Получение определенного ответа")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answers/{answer-id}")
    public ResponseEntity<AnswerDto> getAnswerById(@PathVariable("answer-id") Long answerId) {
        return ResponseEntity.ok(answersService.getAnswerById(answerId));
    }

    @ApiOperation(value = "Получение вопроса по опредленному ответу")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answers/{answer-id}/question")
    public ResponseEntity<QuestionDto> getTeQuestionByAnswerId(@PathVariable("answer-id") Long answerId) {
        return ResponseEntity.ok(questionsService.getQuestionByAnswerId(answerId));
    }

    @ApiOperation(value = "Получение всех результатов по определенному ответу")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answers/{answer-id}/results")
    public ResponseEntity<List<ResultDto>> getResultsByAnswerId(@PathVariable("answer-id") Long answerId) {
        return ResponseEntity.ok(resultsService.getResultsByAnswerId(answerId));
    }

    @ApiOperation(value = "Добаление ответа")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/questions/{question-id}/answers")
    public ResponseEntity<AnswerDto> addAnswer(@RequestBody AnswerDto answerDto, @PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(answersService.addAnswer(answerDto, questionId));
    }

    @ApiOperation(value = "Изменение ответа")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно изменено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestBody AnswerDto answerDto, @PathVariable("answer-id") Long answerId) {
        return ResponseEntity.ok(answersService.updateAnswer(answerDto, answerId));
    }

    @ApiOperation(value = "Удаление ответа")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity<AnswerDto> deleteAnswer(@PathVariable("answer-id") Long answerId) {
        return ResponseEntity.ok(answersService.deleteAnswer(answerId));
    }

}
