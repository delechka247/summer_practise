package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.util.FileType;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    private String name;
    private UserDto teacher;
    private List<UserDto> curators;
    private List<UserDto> students;
    private List<TopicDto> topics;
    private InvitationsDto invitations;


    public static CourseDto from(Course course) {
        CourseDto result = CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .build();
        if(course.getTeacher() != null){
            result.setTeacher(UserDto.from(course.getTeacher()));
        }
        if (course.getCurators() != null) {
            result.setCurators(UserDto.from(course.getCurators()));
        }
        if (course.getStudents() != null) {
            result.setStudents(UserDto.from(course.getStudents()));
        }
        return result;
    }

    public static List<CourseDto> from(List<Course> courses) {
        return courses.stream().map(CourseDto::from).collect(Collectors.toList());
    }

}
