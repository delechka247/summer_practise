package ru.itis.javalab.videomicroservice.controllers;

//TODO:

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.CourseForm;
import ru.itis.javalab.videomicroservice.services.CoursesService;

@Controller
public class InvitationController {

    @Autowired
    private CoursesService coursesService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/invitation/{invitation-code}")
    public ResponseEntity<CourseDto> addCourse(@PathVariable("invitation-code") String invitationCode,
                                              @RequestHeader("TOKEN") String token,
                                              @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        return ResponseEntity.ok(coursesService.addUserToCourse(invitationCode, token));
    }

}
