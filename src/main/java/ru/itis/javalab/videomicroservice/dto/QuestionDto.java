package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Question;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    private String content;

    private TestDto test;

    private List<AnswerDto> answers;

    public static QuestionDto from(Question question) {
        if (question == null) {
            return null;
        }
        return QuestionDto.builder()
                .id(question.getId())
                .test(TestDto.from(question.getTest()))
                .content(question.getContent())
                .answers(AnswerDto.from(question.getAnswers()))
                .build();
    }

    public static List<QuestionDto> from(List<Question> questions) {
        return questions.stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

}
