package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.videomicroservice.models.Result;

import java.util.List;

public interface ResultsRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByAnswer_Id(Long answerId);

    List<Result> findAllByUser_Id(Long userId);

}
