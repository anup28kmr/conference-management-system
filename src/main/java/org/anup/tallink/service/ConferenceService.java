package org.anup.tallink.service;

import org.anup.tallink.dto.ConferenceDto;
import org.anup.tallink.entity.Conference;
import org.anup.tallink.entity.ConferenceRoom;
import org.anup.tallink.entity.ConferenceStatus;
import org.anup.tallink.entity.RoomStatus;
import org.anup.tallink.exceptions.InvalidRoomStatusException;
import org.anup.tallink.exceptions.ResourceNotFoundException;
import org.anup.tallink.repository.ConferenceRepository;
import org.anup.tallink.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceDto createConference(ConferenceDto conferenceDto) throws InvalidRoomStatusException {
        ConferenceRoom room = conferenceRoomRepository.findById(conferenceDto.getConferenceRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Conference room not found"));

        if (!room.getStatus().equals(RoomStatus.AVAILABLE)) {
            throw new InvalidRoomStatusException("Room is not available for booking");
        }

        Conference conference = new Conference();
        conference.setConferenceRoom(room);
        conference.setDateTime(conferenceDto.getDateTime());
        conference.setMaxParticipants(conferenceDto.getMaxParticipants());
        conference.setStatus(ConferenceStatus.SCHEDULED);
        Conference savedConference = conferenceRepository.save(conference);

        return convertToDto(savedConference);
    }

    private ConferenceDto convertToDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setConferenceRoomId(conference.getConferenceRoom().getId());
        dto.setDateTime(conference.getDateTime());
        dto.setMaxParticipants(conference.getMaxParticipants());
        return dto;
    }
}
