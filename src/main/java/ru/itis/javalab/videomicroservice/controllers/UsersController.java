package ru.itis.javalab.videomicroservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.videomicroservice.dto.ResultDto;
import ru.itis.javalab.videomicroservice.services.ResultsService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private ResultsService resultsService;

    @ApiOperation(value = "Получение всех результатов по определенному пользователю")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно получено")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{user-id}/results")
    public ResponseEntity<List<ResultDto>> getResultsByUserId(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(resultsService.getResultsByUserId(userId));
    }

}
