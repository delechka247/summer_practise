package ru.itis.javalab.videomicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.videomicroservice.dto.CourseDto;
import ru.itis.javalab.videomicroservice.dto.TopicDto;
import ru.itis.javalab.videomicroservice.services.CoursesService;
import ru.itis.javalab.videomicroservice.services.TopicsService;

import java.util.List;

@RestController
public class TopicsController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private CoursesService coursesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses/{course-id}/topics")
    public ResponseEntity<List<TopicDto>> getCoursesTopics(@PathVariable("course-id") Long courseId,
                                           @RequestHeader("TOKEN") String token,
                                           @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if (coursesService.hasUserCourse(token, courseId))
            return ResponseEntity.ok(topicsService.getTopicsByCourseId(courseId));
        else
            return (ResponseEntity<List<TopicDto>>) ResponseEntity.status(HttpStatus.FORBIDDEN);

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/courses/{course-id}/topics")
    public ResponseEntity<TopicDto> addCoursesTopic(@PathVariable("course-id") Long courseId, @RequestBody TopicDto topicDto,
                                    @RequestHeader("TOKEN") String token,
                                    @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if( coursesService.isUserTeacher(token, courseId))
            return ResponseEntity.ok(topicsService.addTopic(topicDto, courseId));
        else
            return (ResponseEntity<TopicDto>) ResponseEntity.status(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/courses/{course-id}/topics/{topic-id}")
    public ResponseEntity<TopicDto> getCoursesTopic(@PathVariable("course-id") Long courseId, @PathVariable("topic-id") Long topicId,
                                    @RequestHeader("TOKEN") String token,
                                    @RequestHeader("REFRESH-TOKEN") String refreshToken) {

        if (coursesService.hasUserCourse(token, courseId))
            return ResponseEntity.ok(topicsService.getTopicById(topicId));
        else
            return (ResponseEntity<TopicDto>) ResponseEntity.status(HttpStatus.FORBIDDEN);
    }

}
