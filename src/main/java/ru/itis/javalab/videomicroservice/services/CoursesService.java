package ru.itis.javalab.videomicroservice.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.CourseForm;

import java.util.List;

public interface CoursesService {
    List<CourseDto> getAllCourses();
    CourseDto addCourse(CourseForm courseForm, String token);
    CourseDto getCourseById(Long courseId);
    CourseDto addUserToCourse(String invitationCode, String token);
    boolean hasUserCourse(String token, Long courseId);
    boolean isUserTeacher(String token, Long courseId);

}
