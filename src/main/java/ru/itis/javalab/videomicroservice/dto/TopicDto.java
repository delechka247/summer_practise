package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.models.FileInfo;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.util.FileType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {
    private Long id;
    private Course course;
    private String name;
    private List<FileInfoDto> videoFiles;
    private List<FileInfoDto> textFiles;

    public static TopicDto from(Topic topic) {
        TopicDto result = TopicDto.builder()
                .id(topic.getId())
                .course(topic.getCourse())
                .name(topic.getName())
                .textFiles(topic.getFiles().stream()
                        .filter(x -> x.getFileType().equals(FileType.SYNOPSES))
                        .map(x -> FileInfoDto.from(x))
                        .collect(Collectors.toList()))
                .videoFiles(topic.getFiles().stream()
                        .filter(x -> x.getFileType().equals(FileType.VIDEO))
                        .map(x -> FileInfoDto.from(x))
                        .collect(Collectors.toList()))
                .build();
        return result;
    }

    public static List<TopicDto> from(List<Topic> topics) {
        return topics.stream().map(TopicDto::from).collect(Collectors.toList());
    }
}
