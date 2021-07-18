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
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
