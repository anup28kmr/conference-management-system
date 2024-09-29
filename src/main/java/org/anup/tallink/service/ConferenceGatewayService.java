package org.anup.tallink.service;
import org.anup.tallink.dto.AvailableConferenceDto;
import org.anup.tallink.dto.FeedbackDto;
import org.anup.tallink.dto.RegistrationRequestDto;
import org.anup.tallink.dto.RegistrationResponseDto;
import org.anup.tallink.entity.Conference;
import org.anup.tallink.entity.ConferenceStatus;
import org.anup.tallink.entity.Feedback;
import org.anup.tallink.entity.Participant;
import org.anup.tallink.exceptions.InvalidConferenceUpdateException;
import org.anup.tallink.exceptions.ResourceNotFoundException;
import org.anup.tallink.exceptions.RoomCapacityExceededException;
import org.anup.tallink.repository.ConferenceRepository;
import org.anup.tallink.repository.FeedbackRepository;
import org.anup.tallink.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ConferenceGatewayService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Find available conferences in the date-time range
    public List<AvailableConferenceDto> findAvailableConferences(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return conferenceRepository.findAvailableConferences(startDateTime, endDateTime);
    }

    // Self-registration for a conference
    public RegistrationResponseDto selfRegister(Long conferenceId, RegistrationRequestDto registrationRequest) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Conference not found"));

        // Validate conference status and max capacity
        if (conference.getStatus() == ConferenceStatus.CANCELED) {
            throw new InvalidConferenceUpdateException("Conference is canceled, cannot register.");
        }
        if (participantRepository.countParticipantsByConferenceId(conferenceId) >= conference.getMaxParticipants()) {
            throw new RoomCapacityExceededException("Conference room has reached its maximum capacity.");
        }

        // Generate unique participant code
        String uniqueCode = UUID.randomUUID().toString();

        // Register participant
        Participant participant = new Participant();
        participant.setFirstName(registrationRequest.getFirstName());
        participant.setLastName(registrationRequest.getLastName());
        participant.setEmail(registrationRequest.getEmail());
        participant.setDateOfBirth(registrationRequest.getDateOfBirth());
        participant.setGender(registrationRequest.getGender());
        participant.setConference(conference);
        participant.setUniqueCode(uniqueCode);
        participantRepository.save(participant);

        return new RegistrationResponseDto(uniqueCode, "Successfully registered");
    }

    // Cancel registration
    public void cancelRegistration(String participantCode) {
        Participant participant = participantRepository.findByUniqueCode(participantCode)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
        participantRepository.delete(participant);
    }

    // Send feedback
    public void sendFeedback(String participantCode, FeedbackDto feedbackDto) {
        Participant participant = participantRepository.findByUniqueCode(participantCode)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));

        Feedback feedback = new Feedback();
        feedback.setContent(feedbackDto.getContent());
        feedback.setParticipant(participant);
        feedbackRepository.save(feedback);
    }
}
