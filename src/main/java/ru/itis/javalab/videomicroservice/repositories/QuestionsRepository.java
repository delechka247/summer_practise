package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.videomicroservice.models.Answer;
import ru.itis.javalab.videomicroservice.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByTest_Id(Long testId);

    Optional<Question> findQuestionByAnswersContains(Answer answer);

}
