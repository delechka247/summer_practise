package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Result;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto {

    private Long id;

    private UserDto user;

    private AnswerDto answer;

    private Result.Correctness correctness;

    public static ResultDto from(Result result) {
        if (result == null) {
            return null;
        }
        return ResultDto.builder()
                .id(result.getId())
                .user(UserDto.from(result.getUser()))
                .answer(AnswerDto.from(result.getAnswer()))
                .build();
    }

    public static List<ResultDto> from(List<Result> results) {
        return results.stream()
                .map(ResultDto::from)
                .collect(Collectors.toList());
    }

}
