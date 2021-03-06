package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.models.Topic;

import java.util.Optional;

@Repository
public interface TopicsRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findTopicByTestsContains(Test test);

}
