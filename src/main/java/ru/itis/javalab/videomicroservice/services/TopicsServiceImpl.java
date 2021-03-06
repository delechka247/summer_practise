package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.TopicDto;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.repositories.CoursesRepository;
import ru.itis.javalab.videomicroservice.repositories.TestsRepository;
import ru.itis.javalab.videomicroservice.repositories.TopicsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TopicsServiceImpl implements TopicsService{

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private TestsRepository testsRepository;

    @Override
    public TopicDto addTopic(TopicDto topicDto, Long courseId) {
        Course course = coursesRepository.findById(courseId)
                .orElseThrow(IllegalArgumentException::new);
        Topic newTopic = Topic.builder()
                .name(topicDto.getName())
                .course(course)
                .build();
        topicsRepository.save(newTopic);
        return TopicDto.from(newTopic);
    }

    @Override
    public TopicDto getTopicById(Long topicId) {
        TopicDto topicDto = TopicDto.builder().build();
        Optional<Topic> optionalTopic = topicsRepository.findById(topicId);
        if(optionalTopic.isPresent()) {
            topicDto = TopicDto.from(optionalTopic.get());
        }
        return topicDto;
    }

    @Override
    public List<TopicDto> getTopicsByCourseId(Long courseId) {
        Optional<Course> optionalCourse = coursesRepository.findById(courseId);
        List<TopicDto> topics = Collections.singletonList(TopicDto.builder().build());
        if (optionalCourse.isPresent()){
            topics = TopicDto.from(optionalCourse.get().getTopics());
        }
        return topics;
    }

    @Override
    public TopicDto getTopicByTestId(Long testId) {
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);
        return TopicDto.from(topicsRepository.findTopicByTestsContains(test).orElseThrow(IllegalArgumentException::new));
    }
}
