package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.videomicroservice.models.Question;
import ru.itis.javalab.videomicroservice.models.Test;

import java.util.Optional;

public interface TestsRepository extends JpaRepository<Test, Long> {

    Optional<Test> findTestByQuestionsContains(Question question);

}
