package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Test;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {

    private Long id;

    private String name;

    private String title;

    private UserDto creator;

    private List<QuestionDto> questions;

    private TopicDto subject;

    public static TestDto from(Test test) {
        if (test == null) {
            return null;
        }
        return TestDto.builder()
                .id(test.getId())
                .creator(UserDto.from(test.getCreator()))
                .name(test.getName())
                .title(test.getTitle())
                .questions(QuestionDto.from(test.getQuestions()))
                .subject(TopicDto.from(test.getTopic()))
                .build();
    }

    public static List<TestDto> from(List<Test> tests) {
        return tests.stream()
                .map(TestDto::from)
                .collect(Collectors.toList());
    }

}
