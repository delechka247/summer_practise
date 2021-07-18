package ru.itis.javalab.videomicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.services.CoursesService;

import java.util.List;

@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses")
    public List<CourseDto> getCourses(@RequestHeader("AUTHORIZATION") String token) {
        return coursesService.getAllCourses();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses/{course-id}")
    public CourseDto getCourse(@PathVariable Long courseId) {
        return coursesService.getCourseById(courseId);
    }


    @PostMapping("/courses")
    public CourseDto addCourse(@RequestBody CourseDto courseDto) {
        return coursesService.addCourse(courseDto);
    }
}
