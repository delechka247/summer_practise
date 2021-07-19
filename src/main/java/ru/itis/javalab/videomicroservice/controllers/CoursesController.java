package ru.itis.javalab.videomicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.CourseForm;
import ru.itis.javalab.videomicroservice.dto.InvitationsDto;
import ru.itis.javalab.videomicroservice.models.Invitation;
import ru.itis.javalab.videomicroservice.services.CoursesService;
import ru.itis.javalab.videomicroservice.services.InvitationsService;

import java.util.List;

@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private InvitationsService invitationsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getCourses(@RequestHeader("TOKEN") String token, @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses/{course-id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("course-id") Long courseId, @RequestHeader("TOKEN") String token,
                                              @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if (coursesService.hasUserCourse(token, courseId))
            return ResponseEntity.ok(coursesService.getCourseById(courseId));
        else
            return (ResponseEntity<CourseDto>) ResponseEntity.status(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/courses")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseForm courseForm,
                               @RequestHeader("TOKEN") String token,
                               @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        return ResponseEntity.ok(coursesService.addCourse(courseForm, token));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses/{course-id}/invitations")
    public ResponseEntity<InvitationsDto> getCourseInvitations(@PathVariable("course-id") Long courseId, @RequestHeader("TOKEN") String token,
                                                               @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if (coursesService.isUserTeacher(token, courseId))
            return ResponseEntity.ok(invitationsService.getInvitationsByCourseId(courseId));
        else
            return (ResponseEntity<InvitationsDto>) ResponseEntity.status(HttpStatus.FORBIDDEN);
    }
}
