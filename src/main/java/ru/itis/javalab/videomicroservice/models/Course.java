package ru.itis.javalab.videomicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date dateOfCreation;
    private Date dateOfBeginning;

    @ManyToOne
    @JoinColumn(name = "teacher_id",  referencedColumnName = "id")
    private User teacher;

    @ManyToMany
    @JoinTable(name = "courses_curators",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "curator_id", referencedColumnName = "id"))
    private List<User> curators;

    @ManyToMany
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private List<User> students;

    @OneToMany
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private List<Topic> topics;
}
