package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.videomicroservice.models.Result;
import ru.itis.javalab.videomicroservice.models.Test;
import ru.itis.javalab.videomicroservice.models.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByCreatedTestsContains(Test test);

    Optional<User> findUserByResultsContains(Result result);

}
