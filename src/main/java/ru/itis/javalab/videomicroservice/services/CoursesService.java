package ru.itis.javalab.videomicroservice.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.CourseDto;

import java.util.List;

public interface CoursesService {
    List<CourseDto> getAllCourses();
    CourseDto addCourse(CourseDto courseDto);
    CourseDto getCourseById(Long courseId);

}
