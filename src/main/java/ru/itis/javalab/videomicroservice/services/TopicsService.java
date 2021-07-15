package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.TopicDto;

import java.util.List;

public interface TopicsService {
    TopicDto addTopic(TopicDto topicDto);
    TopicDto getTopicById(Long topicId);
    List<TopicDto> getTopicsByCourseId(Long courseId);
}
