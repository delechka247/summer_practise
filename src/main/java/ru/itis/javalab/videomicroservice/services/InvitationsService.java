package ru.itis.javalab.videomicroservice.services;

import ru.itis.javalab.videomicroservice.dto.InvitationsDto;
import ru.itis.javalab.videomicroservice.models.Course;

public interface InvitationsService {
    InvitationsDto addInvitations(Course course);
    InvitationsDto getInvitationsByCourseId(Long courseId);

}
