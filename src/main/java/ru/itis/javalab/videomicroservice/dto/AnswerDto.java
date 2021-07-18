package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Answer;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {

    private Long id;

    private String content;

    private Answer.Accuracy accuracy;

    private QuestionDto question;

    private List<ResultDto> results;

    public static AnswerDto from(Answer answer) {
        if (answer == null) {
            return null;
        }
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .accuracy(answer.getAccuracy())
                .question(QuestionDto.from(answer.getQuestion()))
                .results(ResultDto.from(answer.getResults()))
                .build();
    }

    public static List<AnswerDto> from(List<Answer> answers) {
        return answers.stream()
                .map(AnswerDto::from)
                .collect(Collectors.toList());
    }

}
