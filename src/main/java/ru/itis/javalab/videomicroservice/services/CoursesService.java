package ru.itis.javalab.videomicroservice.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.CourseDto;

public interface CoursesService {
    CourseDto addCourse(CourseDto courseDto);
    CourseDto getCourseById(Long courseId);

}
