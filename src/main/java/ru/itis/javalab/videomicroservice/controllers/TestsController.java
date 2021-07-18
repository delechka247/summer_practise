package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.QuestionDto;
import ru.itis.javalab.videomicroservice.dto.TestDto;
import ru.itis.javalab.videomicroservice.dto.TopicDto;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.security.details.UserDetailsImpl;
import ru.itis.javalab.videomicroservice.services.*;

import java.util.List;

@RestController
public class TestsController {

    @Autowired
    private TestsService testsService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ResultsService resultsService;

    @Autowired
    private TopicsService topicsService;

    @ApiOperation(value = "Получение всех тестов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests")
    public ResponseEntity<List<TestDto>> getTests() {
        return ResponseEntity.ok(testsService.getAllTests());
    }

    @ApiOperation(value = "Получение опредленного теста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests/{test-id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(testsService.getTestById(testId));
    }

    @ApiOperation(value = "Получение всех вопросов по определенному тесту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests/{test-id}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTestId(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(questionsService.getQuestionByTestId(testId));
    }

    @ApiOperation(value = "Получение создателся теста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests/{test-id}/creator")
    public ResponseEntity<UserDto> getCreatorByTestId(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(usersService.findUserByTestId(testId));
    }

    @ApiOperation(value = "Получение всех тем по определенному тесту")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests/{test-id}/subject")
    public ResponseEntity<TopicDto> getSubjectByTestId(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(topicsService.getTopicByTestId(testId));
    }

    @ApiOperation(value = "Добаление теста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/tests")
    public ResponseEntity<TestDto> addTest(@RequestBody TestDto testDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(testsService.addTest(testDto, userDetails));
    }

    @ApiOperation(value = "Изменение теста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно изменено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PatchMapping("/tests/{test-id}")
    public ResponseEntity<TestDto> updateTest(@RequestBody TestDto testDto, @PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(testsService.updateTest(testDto, testId));
    }

    @ApiOperation(value = "Удаление теста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/tests/{test-id}")
    public ResponseEntity<TestDto> deleteTest(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(testsService.deleteTest(testId));
    }

}
