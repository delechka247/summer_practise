package ru.itis.javalab.videomicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.videomicroservice.dto.InvitationsDto;
import ru.itis.javalab.videomicroservice.models.Course;
import ru.itis.javalab.videomicroservice.models.Invitation;
import ru.itis.javalab.videomicroservice.repositories.InvitationsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class InvitationsServiceImpl implements InvitationsService{

    @Autowired
    private InvitationsRepository invitationsRepository;

    @Override
    public InvitationsDto addInvitations(Course course) {
        InvitationsDto invitationsDto = InvitationsDto.builder().build();
        Invitation invitationForCurator = Invitation.builder()
                .course(course)
                .invitationCode(UUID.randomUUID().toString())
                .invitationRole(Invitation.InvitationRole.CURATOR)
                .build();
        Invitation invitationForStudent = Invitation.builder()
                .course(course)
                .invitationCode(UUID.randomUUID().toString())
                .invitationRole(Invitation.InvitationRole.STUDENT)
                .build();
        invitationsDto.setCuratorInvitationCode(invitationsRepository.save(invitationForCurator).getInvitationCode());
        invitationsDto.setStudentInvitationCode(invitationsRepository.save(invitationForStudent).getInvitationCode());
        return invitationsDto;
    }

    @Override
    public InvitationsDto getInvitationsByCourseId(Long courseId) {
        InvitationsDto invitationsDto = InvitationsDto.builder().build();
        List<Invitation> invitations = invitationsRepository.findInvitationsByCourseId(courseId);
        for (Invitation invitation : invitations) {
            if (invitation.getInvitationRole().equals(Invitation.InvitationRole.STUDENT))
                invitationsDto.setStudentInvitationCode(invitation.getInvitationCode());
            else if (invitation.getInvitationRole().equals(Invitation.InvitationRole.CURATOR))
                invitationsDto.setCuratorInvitationCode(invitation.getInvitationCode());
        }
        return invitationsDto;
    }
}
