package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.TopicDto;
import ru.itis.javalab.videomicroservice.repositories.TopicsRepository;

import java.util.List;

//TODO:

@Service
public class TopicsServiceImpl implements TopicsService{

    @Autowired
    private TopicsRepository topicsRepository;

    @Override
    public TopicDto addTopic(TopicDto topicDto) {
        return null;
    }

    @Override
    public TopicDto getTopicById(Long topicId) {
        return null;
    }

    @Override
    public List<TopicDto> getTopicsByCourseId(Long courseId) {
        return null;
    }
}
