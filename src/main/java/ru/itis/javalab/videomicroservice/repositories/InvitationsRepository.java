package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.videomicroservice.models.FileInfo;
import ru.itis.javalab.videomicroservice.models.Invitation;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationsRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findInvitationByInvitationCode(String invitationCode);
    List<Invitation> findInvitationsByCourseId(Long courseId);
}
