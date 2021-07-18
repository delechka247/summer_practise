package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.videomicroservice.models.Answer;
import ru.itis.javalab.videomicroservice.models.Result;

import java.util.List;
import java.util.Optional;

public interface AnswersRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestion_Id(Long questionId);

    Optional<Answer> findAnswerByResultsContains(Result result);

}
