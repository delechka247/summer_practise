package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.repositories.CoursesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService{

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        return CourseDto.from(coursesRepository.findAll());
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        Course newCourse = Course.builder()
                .name(courseDto.getName())
                //TODO: брать препода из жвт токена, препод тот, кто создал курс
                .teacher(courseDto.getTeacher())
                .build();
        coursesRepository.save(newCourse);
        return CourseDto.from(newCourse);
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        CourseDto courseDto = CourseDto.builder().build();
        Optional<Course> optionalCourse = coursesRepository.findById(courseId);
        if(optionalCourse.isPresent()) {
            courseDto = CourseDto.from(optionalCourse.get());
        }
        return courseDto;
    }

    //TODO: добавить к курсу кураторов
    //TODO: добавить к курсу студентов
}
