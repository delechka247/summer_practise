package ru.itis.javalab.videomicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.TopicDto;
import ru.itis.javalab.videomicroservice.services.TopicsService;

import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicsService topicsService;

    @GetMapping("/courses/{course-id}/topics")
    public List<TopicDto> getCoursesTopics(@PathVariable Long courseId) {
        return topicsService.getTopicsByCourseId(courseId);
    }

    @GetMapping("/courses/{course-id}/topics/{topic-id}")
    public TopicDto getCoursesTopics(@PathVariable Long courseId, @PathVariable Long topicId) {
        return topicsService.getTopicById(topicId);
    }

}
