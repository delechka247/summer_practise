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
import ru.itis.javalab.videomicroservice.dto.TestDto;
import ru.itis.javalab.videomicroservice.services.AnswersService;
import ru.itis.javalab.videomicroservice.services.QuestionsService;
import ru.itis.javalab.videomicroservice.services.ResultsService;
import ru.itis.javalab.videomicroservice.services.TestsService;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private TestsService testsService;

    @Autowired
    private ResultsService resultsService;

    @ApiOperation(value = "Получение всех вопросов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDto>> getQuestions() {
        return ResponseEntity.ok(questionsService.getAllQuestions());
    }

    @ApiOperation(value = "Получение определенного вопроса")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questions/{question-id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(questionsService.getQuestionById(questionId));
    }

    @ApiOperation(value = "Получение всех ответов по определенному вопросу")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questions/{question-id}/answers")
    public ResponseEntity<List<AnswerDto>> getAnswersByQuestionId(@PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(answersService.getAnswersByQuestionId(questionId));
    }

    @ApiOperation(value = "Получение теста по опредленному вопросу")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questions/{question-id}/test")
    public ResponseEntity<TestDto> getTestByQuestionId(@PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(testsService.getTestByQuestionId(questionId));
    }

    @ApiOperation(value = "Добаление вопроса")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/tests/{test-id}/questions")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto questionDto, @PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(questionsService.addQuestion(questionDto, testId));
    }

    @ApiOperation(value = "Изменение вопроса")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно изменено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PatchMapping("/questions/{question-id}")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto, @PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(questionsService.updateQuestion(questionDto, questionId));
    }

    @ApiOperation(value = "Удаление вопроса")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/questions/{question-id}")
    public ResponseEntity<QuestionDto> deleteQuestion(@PathVariable("question-id") Long questionId) {
        return ResponseEntity.ok(questionsService.deleteQuestion(questionId));
    }

}
