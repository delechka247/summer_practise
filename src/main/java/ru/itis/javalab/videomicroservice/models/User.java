package ru.itis.javalab.videomicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private Integer age;

    private String email;

    private String password;

    private String confirm_code;

    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherCourses;

    @ManyToMany(mappedBy = "curators")
    private List<Course> curatorCourses;

    @ManyToMany(mappedBy = "students")
    private List<Course> studentCourses;


    @Enumerated(value = EnumType.STRING)
    private State state;
    public enum State {
        ACTIVE, BANNED
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;
    public enum Role {
        ADMIN, STUDENT, TEACHER, CURATOR
    }

    @Enumerated(value = EnumType.STRING)
    private Status status;
    public enum Status {
        CONFIRMED, UNCONFIRMED
    }

    public Boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public Boolean isBanned() {
        return this.state == State.BANNED;
    }

    public Boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public Boolean isUser() {
        return this.role == Role.STUDENT;
    }

    public Boolean isConfirmed() {
        return this.status == Status.CONFIRMED;
    }

    public Boolean isUnconfirmed() {
        return this.status == Status.UNCONFIRMED;
    }

}
