package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.AnswerDto;
import ru.itis.javalab.videomicroservice.dto.ResultDto;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.security.details.UserDetailsImpl;
import ru.itis.javalab.videomicroservice.services.AnswersService;
import ru.itis.javalab.videomicroservice.services.ResultsService;
import ru.itis.javalab.videomicroservice.services.UsersService;

import java.util.List;

@RestController
public class ResultsController {

    @Autowired
    private ResultsService resultsService;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Получение всех результатов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/results")
    public ResponseEntity<List<ResultDto>> getResults() {
        return ResponseEntity.ok(resultsService.getAllResults());
    }

    @ApiOperation(value = "Получение определенного результата")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/results/{result-id}")
    public ResponseEntity<ResultDto> getResultById(@PathVariable("result-id") Long resultId) {
        return ResponseEntity.ok(resultsService.getResultById(resultId));
    }

    @ApiOperation(value = "Получение ответа по опредленному результату")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/results/{result-id}/answer")
    public ResponseEntity<AnswerDto> getAnswerByResultId(@PathVariable("result-id") Long resultId) {
        return ResponseEntity.ok(answersService.getAnswersByResultId(resultId));
    }

    @ApiOperation(value = "Получение пользователя по опредленному результату")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/results/{result-id}/user")
    public ResponseEntity<UserDto> getUserByResultId(@PathVariable("result-id") Long resultId) {
        return ResponseEntity.ok(usersService.getUserByResultId(resultId));
    }

    @ApiOperation(value = "Добаление результат")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/answers/{answer-id}/results")
    public ResponseEntity<ResultDto> addResult(@RequestBody ResultDto resultDto, @PathVariable("answer-id") Long answerId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(resultsService.addResult(resultDto, answerId, userDetails));
    }

    @ApiOperation(value = "Изменение результата")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно изменено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @PatchMapping("/results/{result-id}")
    public ResponseEntity<ResultDto> updateResult(@RequestBody ResultDto resultDto, @PathVariable("result-id") Long resultId) {
        return ResponseEntity.ok(resultsService.updateResult(resultDto, resultId));
    }

    @ApiOperation(value = "Удаление результата")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно удалено")})
    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/results/{result-id}")
    public ResponseEntity<ResultDto> deleteResult(@PathVariable("result-id") Long resultId) {
        return ResponseEntity.ok(resultsService.deleteResult(resultId));
    }

    @ApiOperation(value = "Получение количества правильных ответов в тесте определенного пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tests/{test-id}/users/{user-id}/resultsCount")
    public ResponseEntity<Integer> getCountOfCorrectAnswersInTest(@PathVariable("test-id") Long testId, @PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(resultsService.getCountOfCorrectAnswersInTest(testId, userId));
    }

}
