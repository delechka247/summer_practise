package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.models.User;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Long id;

    private String name;

    private User teacher;

    private List<User> curators;

    private List<User> students;

    private List<Topic> topics;
}
