package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.CourseForm;
import ru.itis.javalab.videomicroservice.dto.InvitationsDto;
import ru.itis.javalab.videomicroservice.dto.UserDto;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.models.Invitation;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.repositories.CoursesRepository;
import ru.itis.javalab.videomicroservice.repositories.InvitationsRepository;
import ru.itis.javalab.videomicroservice.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private InvitationsService invitationsService;

    @Autowired
    private InvitationsRepository invitationsRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        return CourseDto.from(coursesRepository.findAll());
    }

    @Override
    public CourseDto addCourse(CourseForm courseForm, String token) {
        UserDto teacherDto = usersService.getUserFromJwt(token);
        User teacher = usersRepository.findUserById(teacherDto.getId()).get();
        Course newCourse = Course.builder()
                .name(courseForm.getName())
                .teacher(teacher)
                .build();
        Course course = coursesRepository.save(newCourse);
        InvitationsDto invitationsDto = invitationsService.addInvitations(course);
        CourseDto result = CourseDto.from(course);
        result.setInvitations(invitationsDto);
        return result;
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        CourseDto courseDto = CourseDto.builder().build();
        Optional<Course> optionalCourse = coursesRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            courseDto = CourseDto.from(optionalCourse.get());
        }
        return courseDto;
    }

    @Override
    public CourseDto addUserToCourse(String invitationCode, String token) {
        UserDto userDto = usersService.getUserFromJwt(token);
        User user = usersRepository.findUserById(userDto.getId()).orElseThrow(IllegalArgumentException::new);
        Invitation invitation = invitationsRepository.findInvitationByInvitationCode(invitationCode).orElseThrow(IllegalArgumentException::new);
        Course course = coursesRepository.findById(invitation.getCourse().getId()).orElseThrow(IllegalArgumentException::new);
        if (invitation.getInvitationRole().equals(Invitation.InvitationRole.STUDENT)) {
            course.getStudents().add(user);
        } else if (invitation.getInvitationRole().equals(Invitation.InvitationRole.CURATOR)) {
            course.getCurators().add(user);
        }
        coursesRepository.save(course);
        return CourseDto.from(course);
    }

    @Override
    public boolean hasUserCourse(String token, Long courseId) {
        long num = 0;
        Long userId = usersService.getUserFromJwt(token).getId();
        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        num += course.getCurators().stream().filter(x -> x.getId().equals(userId)).count();
        num += course.getStudents().stream().filter(x -> x.getId().equals(userId)).count();
        if (course.getTeacher().getId().equals(userId))
            num++;
        if (num > 0)
            return true;
        return false;
    }

    @Override
    public boolean isUserTeacher(String token, Long courseId) {
        Long userId = usersService.getUserFromJwt(token).getId();
        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        if (course.getTeacher().getId().equals(userId))
            return true;
        return false;
    }

}
